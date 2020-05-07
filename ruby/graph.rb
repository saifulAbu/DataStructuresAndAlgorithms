require "ostruct"
require "pry"
require "pry-nav"

class Graph
  # n x n adjacency matrix
  def init_from_matrix(matrix)
    n = matrix.size
    @graph_list = Array.new(n) {[]}

    for i in (0..(n-1)) do
      for j in (0..(n-1)) do
        @graph_list[i] << OpenStruct.new(:dest => j, :weight => matrix[i][j]) unless matrix[i][j] == Float::INFINITY
      end
    end
  end

  def print
    p @graph_list
  end

  def dfs(start_node)
    path = {}
    visited = {}
    dfs_helper(start_node, visited, path)
    return path
  end

  def dfs_helper(cur_node, visited, path)
    visited[cur_node] = true
    neighbors = @graph_list[cur_node]
    neighbors.each do
      |neighbor|
        next if visited[neighbor.dest]
        dest = neighbor.dest
        path[dest] = cur_node
        dfs_helper(dest, visited, path)
    end
  end

  def bfs(start_node)
    path = {}
    visited = Hash.new() {false}

    queue = Queue.new
    queue.enq start_node

    visited[start_node] = true
    until queue.empty?
      src = queue.deq
      neighbors = @graph_list[src]
      neighbors.each do
        |neighbor|
        dest = neighbor.dest
        next if visited[dest]
        visited[dest] = true
        path[dest] = src
        queue.enq dest
      end
      #binding.pry
    end

    path
  end

end

i = Float::INFINITY
matrix = [
  [i, 1, 1, i, i],
  [i, i, 1, i, i],
  [i, i, i, 1, 1],
  [i, i, i, i, 1],
  [i, i, i, i, i]
 ]

graph = Graph.new()
graph.init_from_matrix(matrix)
#p graph.dfs(0)
p graph.bfs(0)