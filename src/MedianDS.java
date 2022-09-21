public class MedianDS extends Tree {
    MinTreeHeap minHeap;
    MaxTreeHeap maxHeap;

    public int[][] divideTo5(int A[]) {
        int lastCopied = 0;
                int n = A.length;
        int[][] sets;
        if (n % 5 == 0)
            sets = new int[n / 5][5];
        else
            sets = new int[(n / 5)+1 ][5];
        for (int i = 5; i <= A.length; i += 5) {
            for (int j = i - 5; j < i; j++) {
                sets[(i / 5)-1][j % 5] = A[((i / 5) - 1) * 5 + (j % 5)];
                lastCopied = ((i / 5) - 1) * 5 + (j % 5);
            }
        }
        int residuals = A.length - (A.length/5)*5;
        if (lastCopied + 1 < A.length) {
            for (int k = 0; k < residuals; k++) {
                sets[n / 5][k] = A[ (A.length/5)*5 + k];
            }
        }
        return sets;
    }

    public int Partition(int A[], int p, int r, int x) {
        int n = r - p;
        int q;
        int B[] = new int[n];
        int left = 0;
        int right = n;
        for (int i = 0; i < n ; i++) {
            if (A[i + p] > x) {
                B[right-1] = A[i + p];
                right--;
            }
            else if (A[i + p] < x) {
                    B[left] = A[i + p];
                    left++;
            }

        }
        q = left;
        B[q] = x;
        for (int i = 0; i < n; i++) {
            A[i + p] = B[i];
        }
        return p + q ;
    }

    void bubbleSort(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }

    public  int SelectRecursive(int A[]){
        int n = A.length;
        if(n==1)
            return A[0];
        int arraysOf5[][] = divideTo5(A);
        int N = arraysOf5.length;
        int[] arrayOfMedian= new int[N] ;


        for (int i = 0; i < N-1; i++) {
            bubbleSort(arraysOf5[i]);
            arrayOfMedian[i] = arraysOf5[i][2];
        }

        if(n%5==0) {
            bubbleSort(arraysOf5[N-1]);
            arrayOfMedian[N-1] = arraysOf5[N-1][2];
        }
        else{
            int resNum = n%5;
            int res[] = new int[resNum];
            for (int i = 0; i < resNum ; i++) {
                    res[i] = arraysOf5[N-1][i];
            }
            bubbleSort(res);
            arrayOfMedian[N-1] = res[(resNum -1)/ 2];
        }
        return SelectRecursive(arrayOfMedian);
    }

    public int Select(int A[], int k) {
        int n = A.length;
        int x;
            x=  SelectRecursive(A);
        int q= Partition(A,0,n,x);
        if(q==k)
            return x;
        int  NewArray [];
        if(k<q){
            NewArray=new int[q];
            for(int i=0;i<q;i++)
                NewArray[i]=A[i];
              return Select(NewArray,k);
        }
        else{
            NewArray=new int [n-q-1];
            for(int i=q+1;i<n;i++)
                NewArray[i-q-1]=A[i];
              return Select(NewArray,k-q-1);
        }

    }
    public MedianDS(int A[]){
        int n=A.length;
        int x;
        if(A==null){
            return;
        }
        if(n==1){
            x=A[0];
        }
        x= Select(A,(n/2)-1);
        int minArray[]=new int[n/2];
        int maxArray[]=new int[(n+1)/2];
        for(int i=0,max=0,min=0;i<n;i++){
            if(A[i]>x){
                minArray[min]=A[i];
                min++;
            }
            if(A[i]<=x) {
                maxArray[max] = A[i];
                max++;
            }
        }
        this.maxHeap=MaxTreeHeap.BuildHeapT(maxArray);
        this.minHeap=MinTreeHeap.BuildHeapT(minArray);

    }
    public void insert(int x) {
        if (x > this.maxHeap.root.key) {
            this.minHeap.HeapInsert(x);
            if (minHeap.heap_size == maxHeap.heap_size + 1) {
                int k = minHeap.HeapExtractMin();
                this.maxHeap.HeapInsert(k);
            }
        } else {
            this.maxHeap.HeapInsert(x);
            if(this.minHeap.heap_size==this.maxHeap.heap_size-2) {
                int k= maxHeap.HeapExtractMax();
                this.minHeap.HeapInsert(k);
            }
        }
    }
    public void delMedian() {
        this.maxHeap.HeapExtractMax();
        if (minHeap.heap_size == maxHeap.heap_size + 1) {
            int k = minHeap.HeapExtractMin();
            this.maxHeap.HeapInsert(k);
        }

    }
    public int findMedian(){
        return this.maxHeap.root.key;
    }

}
