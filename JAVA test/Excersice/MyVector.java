/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Excersice;

/**
 *
 * @author Daniel
 */
public class MyVector implements Cloneable {

    public Object[] data;
    private static final int INITIAL_CAPACITY = 100;
    private int size;

    //constructor
    public MyVector() {
        data = new Object[INITIAL_CAPACITY];
        size = 0;
    }
    
    //This will append the element at the end of the vector
    public void append(Object element) {
        if (size == data.length) {
            expand();
        }

        data[size++] = element;

    }

    //this will expand the vector to twice the size by creating a new vector
    //and copying the data into the new vector
    private void expand() {

        Object[] temp = new Object[2 * data.length];
        for (int i = 0; i < size; ++i) {
            temp[i] = data[i];
        }

        data = temp;
    }

    //This will make the vector collection empty
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    //this will check whether the vector contains the element
    public boolean contains(Object element) {
        return indexOf(element) != -1;
    }

    //this will access the element at a given index
    public Object elementAt(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        return data[index];
    }

    //this will find the index of the given element
    public int indexOf(Object element) {
        for (int j = 0; j < size; j++) {
            if (element.equals(data[j])) {
                return j;
            }
        }
        return -1;
    }

    //this will insert the element at the given index
    public boolean insertAt(int index, Object element) {
        if (index < 0 || index > size - 1 || size == data.length) {
            return false;
        }

        for (int j = size - 1; j >= index; j--) {
            data[j + 1] = data[j];
        }
        data[index] = element;
        size++;
        return true;
    }

    //this will check to see if the vector is empty
    public boolean isEmpty() {
        return size == 0;
    }

    //this will remove the element at the given index
    public Object removeAt(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        Object temp = data[index];
        while (index < size - 1) {
            data[index] = data[index + 1];
            index++;
        }
        data[--size] = null;
        return temp;
    }

    //this will remove the element from the given vector
    public boolean remove(Object element) {
        int pos = indexOf(element);
        if (pos == -1) {
            return false;
        }
        removeAt(pos);
        return true;
    }

    //this will replace the element at the given index with the given element
    public boolean replace(int index, Object element) {
        if (index < 0 || index > size - 1) {
            return false;
        }
        data[index] = element;
        return true;
    }

    //This will return the number of elements in the vector
    public int size() {
        return size;
    }

    //this will make sure the vector gets atleast the given capacity
    public void ensureCapacity(int minCapacity) {
        if (minCapacity <= data.length) {
            return;
        }
        Object[] temp = new Object[minCapacity];
        for (int i = 0; i < size; ++i) {
            temp[i] = data[i];
        }

        data = temp;
    }

    //this will return a cloned copy of the vector
    public Object clone() {
        
        MyVector vecCopy = new MyVector();
        for (int i = 0; i < size; ++i) {
            vecCopy.data[i] = data[i];
        }
        return vecCopy;
    }

    //this will remove the vector elements inbetween the fromIndex to the
    //toIndex
    public void removeRange(int fromIndex, int toIndex) {
        if (fromIndex >= toIndex) {
            return;
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (toIndex >= size) {
            toIndex = size;
        }
        int num = toIndex - fromIndex;
        for (int i = fromIndex; i < size - num; ++i) {
            data[i] = data[i + num];

        }
        for (int j = size - num; j < size; ++j) {
            data[j] = null;
        }
        size -= num;
    }

    //this will return a string representation of this vector, with
    //the string representation of each element ex. 1:78    2:45
    public String toString() {
        String str = "The numbers are: \n";
        for (int i = 0; i < size; ++i) {
            str += i + ":  " + data[i] + "\t";
            if ((i + 1) % 2 == 0) {
                str += "\n";
            }
        }
        str += "\n ********************************** \n";
        return str;
    }

    //this will reverse all of the elements in the vector
    public void reverse() {
        Object temp;
        for (int i = 0; i < size / 2; ++i) {
            temp = data[i];
            data[i] = data[size - 1 - i];
            data[size - 1 - i] = temp;
        }
    }

    //this will add all of the elements in vector 2 which we create in the
    //line to the end of this vector
    public void merge(MyVector vector2) {
        for (int i = 0; i < vector2.size; ++i) {
            append(vector2.data[i]);

        }
    }
}

