public class PQ{

  private int aSize;
  private int heapSize;
  private Vertex[] heap;
  private int type;

  public PQ(int type, int size){
    aSize = size;
    heap = new Vertex[size];
    this.type = type;
    heapSize = 0;
  }
  public boolean isEmpty(){
    return (heapSize == 0);
  }
  private int left(int i){
    return 2 * i + 1;
  }
  private int right(int i){
    return 2 * i + 2;
  }
  private int parent(int i){
    return (i - 1) / 2;
  }
  private void heapify(int i){
    int leftChild = left(i);
    int rightChild = right(i);
    int smallest;
    Vertex temp;
    if (leftChild <= heapSize - 1 && heap[leftChild].getWeight(type) < heap[i].getWeight(type)){
      smallest = leftChild;
    }
    else{
      smallest = i;
    }
    if (rightChild <= heapSize - 1 && heap[rightChild].getWeight(type) < heap[smallest].getWeight(type)){
      smallest = rightChild;
    }
    if (smallest !=i){
      temp = new Vertex(heap[smallest]);
      heap[smallest] = heap[i];
      heap[i] = temp;
      heapify(smallest);
    }
  }
  public void remove (Vertex v){
    for (int i = 0; i < heapSize; i++){
      if (v.name.equals(heap[i].name)){
        heap[i] = heap[heapSize - 1];
        heapSize--;
        heapify(i);
      }
    }
  }
  public Vertex extractMin(){
    Vertex min = new Vertex(heap[0]);
    heap[0] = heap[heapSize - 1];
    heapSize--;
    heapify(0);
    return min;
  }
  public void insert(Vertex v){
    if(heapSize == heap.length){
      System.out.println("The heap is full");
    }
    else{
      heapSize++;
      heap[heapSize - 1] = v;
      heapify(heapSize - 1);
    }
  }
  public static void main(String args[]){
    PQ pq = new PQ (1, 15);
    Vertex v = new Vertex("test");
    pq.insert(v);
    System.out.println(pq.extractMin().minDistance);
  }
}
