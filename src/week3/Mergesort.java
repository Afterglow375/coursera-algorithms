package week3;

// Recursive mergesort implementation
public class Mergesort {

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		
		// Copy the contents of the array to an auxillary array
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}

		
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) 
				a[k] = aux[j++];
			else if (j > hi) 
				a[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}
	}
	
	private static void sort(Comparable [] a, Comparable[] aux, int lo, int hi) {
		if (hi <= lo) return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid+1, hi);
		if (!less(a[mid+1], a[mid])) return;
		merge(a, aux, lo, mid, hi);
	}
	
	private static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length -1);
	}
	
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	private static void exch(Comparable[] a, int i, int j) {
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	public static void main(String[] args) {
		Integer[] test = { 2, 5, -10, 15, 20, 7, 9 };
		sort(test);
		for (int i : test) 
			System.out.println(i);
	}
}
