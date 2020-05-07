require "pry"
require "pry-nav"

def dfs(matrix, src)
  visited = {}
  path = {}
  dfs_helper(matrix, src, visited, path)
  return path
end

def dfs_helper(matrix, src, visited, path)
  return if visited[src]
  visited[src] = true
  neighbors = matrix[src]
  for dest in (0..(neighbors.size - 1)) do
    next if neighbors[dest] == Float::INFINITY
    unless visited[dest]
      path[dest] = src
      dfs_helper(matrix, dest, visited, path)
    end
  end
end

def bfs(matrix, src)
  path = {}
  visited = Hash.new() {false}
  queue = Queue.new
  queue.enq(src)
  visited[src] = true

  until queue.empty?
    #binding.pry
    src = queue.deq
    dests = matrix[src]

    for dest in (0..(dests.size - 1)) do
      next if visited[dest] or dests[dest] == Float::INFINITY
      visited[dest] = true
      path[dest] = src
      queue.enq dest
    end
  end

  path
end

x = Float::INFINITY
matrix = [
  [x, 1, 1, x, x],
  [x, x, 1, x, x],
  [x, x, x, 1, 1],
  [x, x, x, x, 1],
  [x, x, x, x, x]
 ]

p dfs(matrix, 0)
p bfs(matrix, 0)