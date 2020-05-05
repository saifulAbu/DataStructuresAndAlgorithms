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
    path[dest] = src
    dfs_helper(matrix, dest, visited, path)
  end
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