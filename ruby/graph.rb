require "ostruct"

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
end

inf = Float::INFINITY
matrix = [
  [inf, 1, inf, inf],
  [inf, inf, 1, inf],
  [inf, inf, inf, 1],
  [inf, 1, 1, inf]
 ]

graph = Graph.new()
graph.init_from_matrix(matrix)
graph.print