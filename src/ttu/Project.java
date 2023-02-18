package ttu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class Project {
	public static void main(String[] args) {
		int s1[] = new int[10000];
		int s2[] = new int[10000];
		int s3[] = new int[10000];
		int s4[] = new int[10000];
		int s5[] = new int[10000];
		try {
			File s1File = new File("C:\\Users\\Vamsi\\Desktop\\Project1\\source1.txt");
			Scanner s1Reader = new Scanner(s1File);
			File s2File = new File("C:\\Users\\Vamsi\\Desktop\\Project1\\source2.txt");
			Scanner s2Reader = new Scanner(s2File);
			File s3File = new File("C:\\Users\\Vamsi\\Desktop\\Project1\\source3.txt");
			Scanner s3Reader = new Scanner(s3File);
			File s4File = new File("C:\\Users\\Vamsi\\Desktop\\Project1\\source4.txt");
			Scanner s4Reader = new Scanner(s4File);
			File s5File = new File("C:\\Users\\Vamsi\\Desktop\\Project1\\source5.txt");
			Scanner s5Reader = new Scanner(s5File);

			int i = 0;
			while (i < 10000 && s1Reader.hasNextLine()) {
				s1[i] = Integer.parseInt(s1Reader.nextLine());
				s2[i] = Integer.parseInt(s2Reader.nextLine());
				s3[i] = Integer.parseInt(s3Reader.nextLine());
				s4[i] = Integer.parseInt(s4Reader.nextLine());
				s5[i] = Integer.parseInt(s5Reader.nextLine());
				i++;
			}
			s1Reader.close();
			s2Reader.close();
			s3Reader.close();
			s4Reader.close();
			s5Reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} catch (NumberFormatException a) {
			System.out.println("An error occurred.");
			a.printStackTrace();
		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		int combined[] = new int[10000];
		for (int i = 0; i < 10000; i++) {
			combined[i] = s1[i] + s2[i] + s3[i] + s4[i] + s5[i];
		}
		int combinedIndex[] = new int[10000];
		List<IndexValuePair> combinedIndexValue = sortArrayWhileMaintainingIndex(combined);
		int s1rearranged[] = new int[10000];
		int s2rearranged[] = new int[10000];
		int s3rearranged[] = new int[10000];
		int s4rearranged[] = new int[10000];
		int s5rearranged[] = new int[10000];
		int k=0;
		for(IndexValuePair obj:combinedIndexValue) {
			int index = obj.index;
			s1rearranged[k] = s1[index];
			s2rearranged[k] = s2[index];
			s3rearranged[k] = s3[index];
			s4rearranged[k] = s4[index];
			s5rearranged[k] = s5[index];
			k++;
		}
		
		
		
		
		MergeSort M1 = new MergeSort();
		M1.sort(s1rearranged, 0, s1rearranged.length-1);
		System.out.println(M1.getInversions());
		MergeSort M2 = new MergeSort();
		M2.sort(s2rearranged, 0, s2rearranged.length-1);
		System.out.println(M2.getInversions());
		MergeSort M3 = new MergeSort();
		M3.sort(s3rearranged, 0, s3rearranged.length-1);
		System.out.println(M3.getInversions());
		MergeSort M4 = new MergeSort();
		M4.sort(s4rearranged, 0, s4rearranged.length-1);
		System.out.println(M4.getInversions());
		MergeSort M5 = new MergeSort();
		M5.sort(s5rearranged, 0, s5rearranged.length-1);
		System.out.println(M5.getInversions());
		
		QuickSort Q1 = new QuickSort();
		Q1.sort(s1rearranged, 0, s1rearranged.length-1);
		System.out.println(Q1.getInversions());
		QuickSort Q2 = new QuickSort();
		Q2.sort(s2rearranged, 0, s2rearranged.length-1);
		System.out.println(Q2.getInversions());
		QuickSort Q3 = new QuickSort();
		Q3.sort(s3rearranged, 0, s3rearranged.length-1);
		System.out.println(Q3.getInversions());
		QuickSort Q4 = new QuickSort();
		Q4.sort(s4rearranged, 0, s4rearranged.length-1);
		System.out.println(Q4.getInversions());
		QuickSort Q5 = new QuickSort();
		Q5.sort(s5rearranged, 0, s5rearranged.length-1);
		System.out.println(Q5.getInversions());
		
	}
	
	static class IndexValuePair {
        int index;
        int value;

        IndexValuePair(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
	
	public static List<IndexValuePair> sortArrayWhileMaintainingIndex(int[] arr) {
        List<IndexValuePair> indexValuePairs = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            indexValuePairs.add(new IndexValuePair(i, arr[i]));
        }

        Collections.sort(indexValuePairs, new Comparator<IndexValuePair>() {
            @Override
            public int compare(IndexValuePair o1, IndexValuePair o2) {
                return Integer.compare(o1.value, o2.value);
            }
        });

        return indexValuePairs;
    }
	
	
}


class MergeSort {
    private int inversions = 0;
    public int getInversions() {
    	return inversions;
    }
    public void sort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            sort(arr, l, m);
            sort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }
    
    public void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[m + 1 + j];
        }
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
                inversions += n1 - i;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

}


 class QuickSort {
    private int countInversions = 0;
    
    public int getInversions() {
    	return countInversions;
    }
    
    public void sort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            sort(arr, low, pivotIndex - 1);
            sort(arr, pivotIndex + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
                countInversions += j - i;
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}


//quicksort replacement written by: Brandon Bratcher
/*
import java.util.ArrayList;
import java.util.Random;

public class QuickSort {
	private int[] unsorted;
	private int[] sorted;
	private int inversions;
	
	//constructor
	QuickSort(int[] unsorted_) {
		//set unsorted value
		this.unsorted = unsorted_;
		//set inversions to 0
		this.inversions = 0;
		
		//convert unsorted into an ArrayList of type Integer
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (int i=0; i<this.unsorted.length; i++) {
			temp.add(this.unsorted[i]);
		}
		
		//sort temp(holds the values of unsorted)
		temp = sort(temp);
		
		//transfer sorted values into sorted
		this.sorted = new int[temp.size()];
		for (int i=0; i<temp.size(); i++) {
			this.sorted[i] = temp.get(i);
		}
	}
	
	//setters
	public void setUnsorted(int[] unsorted_) {
		this.unsorted = unsorted_;
	}
	
	//getters
	public int[] getUnsorted() {
		return unsorted;
	}
	
	public int[] getSorted() {
		return sorted;
	}
	
	public int getInversions() {
		return inversions;
	}
	
	//other methods
	public ArrayList<Integer> sort(ArrayList<Integer> array) {
		if (array.size() <= 1) {
			return array;
		} else {
			ArrayList<Integer> less_than = new ArrayList<Integer>();
			ArrayList<Integer> equal_to = new ArrayList<Integer>();
			ArrayList<Integer> greater_than = new ArrayList<Integer>();
			
			Random rand = new Random();
			int pivot_index = rand.nextInt(array.size());
			int pivot_val = array.get(pivot_index);
			
			for (int i=0; i<array.size(); i++) {
				int element = array.get(i);
				
				if (element < pivot_val) {
					less_than.add(element);
					
					this.inversions += equal_to.size();
					this.inversions += greater_than.size();
				} else if (element == pivot_val) {
					equal_to.add(element);
					
					this.inversions += greater_than.size();
				} else {
					greater_than.add(element);
				}
			}
			
			less_than = sort(less_than);
			greater_than = sort(greater_than);
			
			return merge(less_than, equal_to, greater_than);
		}
	}
	
	public void sort(int[] unsorted_) {
		setUnsorted(unsorted_);
		
		//convert unsorted into an ArrayList of type Integer
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (int i=0; i<this.unsorted.length; i++) {
			temp.add(this.unsorted[i]);
		}
		
		//sort temp(holds the values of unsorted)
		temp = sort(temp);
		
		//transfer sorted values into sorted
		this.sorted = new int[temp.size()];
		for (int i=0; i<temp.size(); i++) {
			this.sorted[i] = temp.get(i);
		}
	}
	
	private ArrayList<Integer> merge(ArrayList<Integer> less, ArrayList<Integer> equal, ArrayList<Integer> greater) {
		ArrayList<Integer> merged = new ArrayList<Integer>();
		
		//concatenate less into merged
		for (int i=0; i<less.size(); i++) {
			merged.add(less.get(i));
		}
		
		//concatenate equal into merged
		for (int i=0; i<equal.size(); i++) {
			merged.add(equal.get(i));
		}
		
		//concatenate greater into merged
		for (int i=0; i<greater.size(); i++) {
			merged.add(greater.get(i));
		}
		
		//return merged
		return merged;
	}
	
	public void display() {
		displayUnsorted();
		displaySorted();
		displayInversions();
	}
	
	public void displayUnsorted() {
		System.out.print("Unsorted array: ");
		for (int i=0; i<this.unsorted.length; i++) {
			if (i==0) {
				System.out.print(this.unsorted[i]);
			} else {
				System.out.print(", " + this.unsorted[i]);
			}
		}
		System.out.println();
	}
	
	public void displaySorted() {
		System.out.print("Unsorted array: ");
		for (int i=0; i<this.sorted.length; i++) {
			if (i==0) {
				System.out.print(this.sorted[i]);
			} else {
				System.out.print(", " + this.sorted[i]);
			}
		}
		System.out.println();
	}
	
	public void displayInversions() {
		System.out.println("Inversion: " + this.inversions);
		System.out.println();
	}
}

*/



