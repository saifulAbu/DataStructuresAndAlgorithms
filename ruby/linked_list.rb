class LinkedList
  class Node
    attr_accessor :val, :next

    def initialize(val)
      @val = val
      @next = nil
    end
  end


  def initialize()
    @size = 0
    @head = Node.new "dummy"
    @tail = @head
  end

  def add_front(val)
    node = Node.new val
    node.next = @head.next
    @head.next = node
    if @size == 0
      @tail = node
    end
    @size += 1
  end

  def add_end(val)
    node = Node.new val
    @tail.next = node
    @tail = node
    @size += 1
  end

  def print_list
    cur = @head.next
    while cur != nil
      print cur.val, " "
      cur = cur.next
    end
    puts
  end

  def size
    return @size
  end

  def delete(val)
    prev = @head
    node = @head.next

    until node.nil?
      break if node.val == val
      prev = prev.next
      node = node.next
    end

    unless node.nil?
      prev.next = node.next
      @tail = prev if node == @tail
      @size -= 1
    end
  end
end


ll = LinkedList.new
ll.add_end 7
ll.add_end 3
ll.add_end 5

ll.print_list
puts ll.size

ll.delete 5
ll.print_list

ll.add_end 22
ll.print_list
