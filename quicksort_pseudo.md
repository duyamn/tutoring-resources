# Pivots
When you think of quicksort think of pivots.

Imagine an array of integers.
A sorted version:
```
1 2 3 4 5 6
```
And an unsorted version:
```
3 2 1 6 4 5
```

Take any value within the array, we call this the pivot.
Now imagine rearranging the values in the array,
place all of the smaller values on one side of the pivot and the larger values on the other side of the pivot,
here we might say that left has the lesser values while right has the greater values.

So with our example we might take `3` to be our pivot.
After rearranging the values we end up with 3 in the spot we would find it in in the sorted array.
```
    v
2 1 3 6 4 5
    ^
```
To either side of 3 is the remaining unsorted portions.

Quicksort is repeated application of this principle upon smaller and smaller subarrays.
The version that is typically taught (and that I will teach to you) uses the rightmost element in the array as its pivot.

# Quicksort
## Pseudocode
I tend to overload my pseudocode with java parlance but here is my pseudocode
```
algo quicksort(int[] A, int start, int end) --[return]--> nothing
    if (end <= start) return
    pivot <-- partition (A, start, end)
    quicksort(A, start, pivot-1)
    quicksort(A, pivot+1, end)

algo partition(int[] A, int start, int end) --[return]--> int
    pivot <-- A[end]
    i <-- start - 1
    for j <-- start...end:
        if A[j] < pivot then:
            i <-- i + 1
            swap (A[j], A[i])
    i <-- i + 1
    swap (A[i], A[end])
    return i
```

## Explanation

The way I've written quicksort above makes use of 2 algorithms:
 - the recursive `quicksort()`
 - and its helper `partition()`

First, let's explain `quicksort()`
```
algo quicksort(int[] A, int start, int end) --[return]--> nothing
    if (end <= start) return
    pivot <-- partition (A, start, end)
    quicksort(A, start, pivot-1)
    quicksort(A, pivot+1, end)
```

`if (end <= start) return` is our base case.
 - If it triggers on the first call then we would be trying to use quicksort on a single element array or even an empty array.
 - It will later trigger on recursive calls as we go into smaller and smaller sub-arrays.
 - Eventually we end up at a single element or empty subarray which is already sorted by its nature.

`pivot <-- partition (A, start, end)`
 - `partition()` will 
   - move the rightmost elment of `A` into the correct position
   - return the position of the element that is now in the correct position

We then recursively call `quicksort()` with the left and right partitions.

Now we can move onto `partition()`
```
algo partition(int[] A, int start, int end) --[return]--> int
    pivot <-- A[end]
    i <-- start - 1
    for j <-- start...end:
        if A[j] < pivot then:
            i <-- i + 1
            swap (A[j], A[i])
    i <-- i + 1
    swap (A[i], A[end])
    return i
```
partition does the bulk of the work, sifting through the elements and placing them at their corresponding sides.

One crucial detail of note: we DO NOT move the pivot until the end of `partition()`'s run

we can have the left side and the right side then move the pivot to the middle after the algo's run.
```
...
    pivot <-- A[end]
    i <-- start - 1
...
```
 - we first take our pivot value
   - the last/rightmost ele of the (sub)array
 - `i` is the last element of the left subarray
 - `i` is initialized as `start-1` since the subarray is empty at the start of `partition()`

```
...
    for j <-- start...end:
        if A[j] < pivot then:
            i <-- i + 1
            swap (A[j], A[i])
...
```
 - `for j <-- start...end:`
   - we're looking at every index from `start` to `end`, inclusively
 - the if statement triggering means that the element `A[j]` is smaller than the pivot, meaning that it belongs to the left array
   - we increment `i` as it represents the last element of the left subarray
     - this lets us grow the subarray by 1 in order to accomodate the element
     - `i` will now be the index to the first member of the right subarray
   - we then swap the two values, switching their places in the array
     - `i` is now be the index to the latest element to be added to the left subarray
```
...
    i <-- i + 1
    swap (A[i], A[end])
    return i
```
 - after exiting the for loop the array will look something like this:
```
               i                 end
               v                 v
[left sub array][right subarray][p]
```
`i <-- i + 1`: we increment i so that it points to the first element of the right subarray
```
                i                end
                v                v
[left sub array][right subarray][p]
```
Then we swap the values at index `i` and index `end`
```
                i                end
                v                v
[left sub array][p][right subarray]
```

`p` is in its correct place and we can return where we had placed `p` in the end for use in `quicksort()`.