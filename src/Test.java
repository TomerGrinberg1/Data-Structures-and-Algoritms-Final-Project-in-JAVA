import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;
import static java.lang.System.out;

interface Constants
{
    int SEED = 3659412;
    int TEST_SIZE = 3000;
    int UNIQUE_KEYS_NUM = TEST_SIZE;
    int MAXIMUM_INSERT_NODES = UNIQUE_KEYS_NUM;
    int UNIQUE_KEYS_MAXIMUM = TEST_SIZE *100;
    int INITIAL_SIZE = TEST_SIZE / 10;
    enum Operations {INSERT, EXTRACT, PRINT}
}

public class Test {
    public static void main(String[] args) throws IOException {
        DataOutputStream outStream = new DataOutputStream(out);

        Random random = new Random();
        // fix the seed to reproduce the run
        random.setSeed(Constants.SEED);
       testMinHeap(random, outStream);
        testMaxHeap(random, outStream);
        testMedianDS(random, outStream);
    }
    public static void testMinHeap(Random random, DataOutputStream outStream) throws IOException
    {
        int[] A = new int[Constants.INITIAL_SIZE];
        int[] uniqueKeys = createUniqueKeys(random);
        int heapSize = Constants.INITIAL_SIZE;
        int uniqueKeysArrayIndex = 0;
        for(int i=0;i<Constants.INITIAL_SIZE;i++)
        {
            A[uniqueKeysArrayIndex] = uniqueKeys[uniqueKeysArrayIndex];
            uniqueKeysArrayIndex++;
        }
        MinTreeHeap heap = MinTreeHeap.BuildHeapT(A);



        Constants.Operations[] operationsArray = generateOperations(random);
        for (int i=0; i<Constants.TEST_SIZE;i++)
        {
            switch (operationsArray[i])
            {
                case INSERT: {
                    if (uniqueKeysArrayIndex < Constants.MAXIMUM_INSERT_NODES){
                        outStream.writeBytes("Inserting " + uniqueKeys[uniqueKeysArrayIndex] + " to heap." + System.lineSeparator());
                        heap.HeapInsert(uniqueKeys[uniqueKeysArrayIndex]);
                        uniqueKeysArrayIndex++;
                        heapSize++;
                    }
                    break;
                }
                case EXTRACT:
                {
                    if(heapSize>0)
                    {
                        int min = heap.HeapExtractMin();
                        heapSize--;
                        outStream.writeBytes("Extracting min key..." + System.lineSeparator());
                        outStream.writeBytes("The minimal key in the heap" +
                                " is " + min + System.lineSeparator());
                    }
                    break;
                }
                case PRINT:
                {
                    outStream.writeBytes("Print in layers min heap:" + System.lineSeparator());
                    heap.printByLayer(outStream);
                    break;
                }
            }
        }


    }

    public static void testMaxHeap(Random random, DataOutputStream outStream) throws IOException
    {
        int[] A = new int[Constants.INITIAL_SIZE];
        int[] uniqueKeys = createUniqueKeys(random);
        int heapSize = Constants.INITIAL_SIZE;
        int uniqueKeysArrayIndex = 0;
        for(int i=0;i<Constants.INITIAL_SIZE;i++)
        {
            A[uniqueKeysArrayIndex] = uniqueKeys[uniqueKeysArrayIndex];
            uniqueKeysArrayIndex++;
        }
        MaxTreeHeap heap = MaxTreeHeap.BuildHeapT(A);
        Constants.Operations[] operationsArray = generateOperations(random);
        for (int i=0; i<Constants.TEST_SIZE;i++)
        {
            switch (operationsArray[i])
            {
                case INSERT:
                {
                    if (uniqueKeysArrayIndex < Constants.MAXIMUM_INSERT_NODES) {
                        outStream.writeBytes("Inserting " + uniqueKeys[uniqueKeysArrayIndex] + " to heap." + System.lineSeparator());
                        heap.HeapInsert(uniqueKeys[uniqueKeysArrayIndex]);
                        uniqueKeysArrayIndex++;
                        heapSize++;
                    }
                    break;
                }
                case EXTRACT:
                {
                    if(heapSize>0)
                    {
                        int max = heap.HeapExtractMax();
                        heapSize--;
                        outStream.writeBytes("Extracting max key..." + System.lineSeparator());
                        outStream.writeBytes("The maximal key in the heap" +
                                " is " + max + System.lineSeparator());
                    }
                    break;
                }
                case PRINT:
                {
                    outStream.writeBytes("Print in layers max heap:" + System.lineSeparator());
                    heap.printByLayer(outStream);
                    break;
                }
            }
        }



    }



    public static void testMedianDS(Random random, DataOutputStream outStream) throws IOException
    {
        int[] A = new int[Constants.INITIAL_SIZE];
        int[] uniqueKeys = createUniqueKeys(random);
        int heapSize = Constants.INITIAL_SIZE;
        int uniqueKeysArrayIndex = 0;
        for(int i=0;i<Constants.INITIAL_SIZE;i++)
        {
            A[uniqueKeysArrayIndex] = uniqueKeys[uniqueKeysArrayIndex];
            uniqueKeysArrayIndex++;
        }
        MedianDS med = new MedianDS(A);

        Constants.Operations[] operationsArray = generateOperations(random);
        for (int i=0; i<Constants.TEST_SIZE;i++)
        {
            switch (operationsArray[i])
            {
                case INSERT:
                {
                    if (uniqueKeysArrayIndex < Constants.MAXIMUM_INSERT_NODES) {
                        outStream.writeBytes("Inserting " + uniqueKeys[uniqueKeysArrayIndex] + " to median DS." + System.lineSeparator());
                        med.insert(uniqueKeys[uniqueKeysArrayIndex]);
                        uniqueKeysArrayIndex++;
                        heapSize++;
                    }
                    break;
                }
                case EXTRACT:
                {
                    if(heapSize>0)
                    {
                        med.delMedian();
                        heapSize--;
                        outStream.writeBytes("Extracting median key..." + System.lineSeparator());

                    }
                    break;
                }
                case PRINT:
                {
                    outStream.writeBytes("The median key in the DS" +
                            " is " + med.findMedian() + System.lineSeparator());
                    break;
                }
            }
        }




    }


    public static Constants.Operations[] generateOperations(Random random)
    {
        Constants.Operations[] operationsValuesArray = Constants.Operations.values();
        Constants.Operations[] operationsArray = new Constants.Operations[Constants.TEST_SIZE];

        for (int i=0;i<Constants.TEST_SIZE;i++)
        {
            // set a random operation
            operationsArray[i] = operationsValuesArray[random.nextInt(operationsValuesArray.length)];
        }
        return operationsArray;
    }
    public static int[] createUniqueKeys(Random random)
    {
        int[] uniqueKeysArray = new int[Constants.UNIQUE_KEYS_NUM];
        int uniqueKeysArrayIndex = 0;

        // create an array to check duplicates
        int[] duplicateKeysArray = new int[Constants.UNIQUE_KEYS_MAXIMUM];
        // initialize the array
        for (int i=0; i<Constants.UNIQUE_KEYS_MAXIMUM; i++)
        {
            duplicateKeysArray[i] = 0;
        }
        // create the unique keys
        while (uniqueKeysArrayIndex < Constants.UNIQUE_KEYS_NUM)
        {
            //returns int in the range [0, Constants.UNIQUE_KEYS_MAXIMUM)
            int key = random.nextInt(Constants.UNIQUE_KEYS_MAXIMUM);
            if (duplicateKeysArray[key] == 0)
            {
                duplicateKeysArray[key] = 1;
                // save the key and make sure it is positive
                uniqueKeysArray[uniqueKeysArrayIndex] = key + 1;
                uniqueKeysArrayIndex += 1;
            }
        }
        return uniqueKeysArray;
    }
}
