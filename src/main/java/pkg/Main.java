package pkg;

public class Main {

    public static void main(String[] args) {
        Object[] objects = {1, 2, 3, 4, 5, 6, 1, 7, 1, 1, 1, 2, 3, 4, 8, 2, 5, 9, 10, 4, 3, 2, 1};
        Object[] removeDuplicate = removeDuplicate(objects);
        for(Object e: removeDuplicate) {
            System.out.print(e.toString() + " ");
        }
    }

    private static Object[] removeDuplicate(Object[] array) {
        int arrayLength = array.length;
        int countDropElement = 0;
        CustomSet customSet = new CustomSet(arrayLength, 3);
        for (int i = 0; i < arrayLength; i++) {
            if(!customSet.add(array[i])) {
                array[i] = null;
                countDropElement++;
            }
        }
        return cleanArray(array, countDropElement);
        //осталось создать новый массив и продублировать все туда
    }

    private static Object[] cleanArray(Object[] array, int countDropElement) {
        int arrayLength = array.length;
        Object[] cleanArray = new Object[arrayLength - countDropElement];
        int j = 0;
        for (Object o : array) {
            if (o != null) {
                cleanArray[j] = o;
                j += 1;
            }
        }
        return cleanArray;
    }

    private static class CustomSet {

        private Node[] array;
        private int initialCapacityForNode;

        CustomSet(int initialCapacity, int initialCapacityForNode) {
            array = new Node[initialCapacity];
            this.initialCapacityForNode = initialCapacityForNode;
        }

        public boolean add(Object element) {
            int hashCode = element.hashCode();
            if (array[hashCode] == null) {
                array[hashCode] = new Node(element, initialCapacityForNode);
                return true;
            } else {
                return array[hashCode].add(element);
            }
        }

        private static class Node {
            private final Object[] array;
            private Node nextNode;
            private final int initialCapacity;

            Node(Object element, int initialCapacity) {
                array = new Object[initialCapacity];
                array[0] = element;
                this.initialCapacity = initialCapacity;
            }

            public boolean add(Object element) {
                int arrayLength = array.length;
                for(int i = 0; i < arrayLength; i++) {
                    if(array[i] != null) {
                        if(array[i].equals(element)) {
                            return false;
                        }
                    } else {
                        array[i] = element;
                        return true;
                    }
                }
                return workWithNextNode(element);
            }

            private boolean workWithNextNode(Object element) {
                if(nextNode == null) {
                    nextNode = new Node(element, initialCapacity);
                    return true;
                } else {
                    return nextNode.add(element);
                }
            }

        }

    }

}
