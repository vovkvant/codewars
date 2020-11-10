package algoexpert.medium.minheap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Do not edit the class below except for the buildHeap,
// siftDown, siftUp, peek, remove, and insert methods.
// Feel free to add new properties and methods to the class.
public class Program {
    public static void main(String args[]) {
        List<Integer> list = Arrays.asList(991,
                -731,
                -882,
                100,
                280,
                -43,
                432,
                771,
                -581,
                180,
                -382,
                -998,
                847,
                80,
                -220,
                680,
                769,
                -75,
                -817,
                366,
                956,
                749,
                471,
                228,
                -435,
                -269,
                652,
                -331,
                -387,
                -657,
                -255,
                382,
                -216,
                -6,
                -163,
                -681,
                980,
                913,
                -169,
                972,
                -523,
                354,
                747,
                805,
                382,
                -827,
                -796,
                372,
                753,
                519,
                906);
        MinHeap minHeap = new MinHeap(list);
        System.out.println(isMinHeapPropertySatisfied(minHeap.heap));
        System.out.println(minHeap.remove());
        System.out.println(isMinHeapPropertySatisfied(minHeap.heap));


    }

    static boolean isMinHeapPropertySatisfied(List<Integer> array) {
        for (int currentIdx = 1; currentIdx < array.size(); currentIdx++) {
            int parentIdx = (currentIdx - 1) / 2;
            if (parentIdx < 0) {
                return true;
            }
            if (array.get(parentIdx) > array.get(currentIdx)) {
                System.out.println("parentIdx=" + parentIdx);
                System.out.println("currentIdx=" + currentIdx);
                return false;
            }
        }

        return true;
    }

    static class MinHeap {

        List<Integer> heap = new ArrayList<Integer>();

        public MinHeap(List<Integer> array) {
            heap = buildHeap(array);
        }

        public List<Integer> buildHeap(List<Integer> array) {
            List<Integer> heap = new ArrayList<>(array.size() + 1);
            for(int i = 0; i < array.size(); i++) {
                heap.add(array.get(i));
                siftUp(i, heap);
            }
            // Write your code here.
            return heap;
        }

        public void siftDown(int currentIdx, int endIdx, List<Integer> heap) {
            // Write your code here.
            currentIdx++;
            while (currentIdx*2 - 1 <= endIdx) {
                int childIdx = currentIdx*2;
                if(!less(heap, childIdx - 1, childIdx)) childIdx++;
                if(less(heap, currentIdx - 1, childIdx - 1)) break;
                exchange(heap, currentIdx - 1, childIdx - 1);
                currentIdx = childIdx;
            }
        }

        public void siftUp(int currentIdx, List<Integer> heap) {
            currentIdx++;
            while (currentIdx > 1 && less(heap, currentIdx -1 , currentIdx/2 -1 )) {
                exchange(heap, currentIdx -1, currentIdx/2- 1);
                currentIdx = currentIdx/2;
            }
        }

        boolean less(List<Integer> heap, int i, int j) {
            if(i < heap.size() && j < heap.size()) {
                return heap.get(i) < heap.get(j);
            } else
                return true;
        }

        void exchange(List<Integer> heap, int i, int j){
            int temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }

        public int peek() {
            // Write your code here.
            return heap.get(0);
        }

        public int remove() {
            // Write your code here.
            int result = heap.get(0);
            exchange(heap, 0, heap.size() - 1);
            heap.remove(heap.size() - 1);
            siftDown(0, heap.size() - 1, heap);
            return result;
        }

        public void insert(int value) {
            // Write your code here.
            heap.add(value);
            siftUp(heap.size() - 1, heap);
        }
    }
}
