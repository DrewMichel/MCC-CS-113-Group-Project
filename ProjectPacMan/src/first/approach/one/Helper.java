package first.approach.one;

/**
 * Created by Julian on 5/10/2017.
 * Helper.java - Has helper methods for the program
 */
public class Helper {


    /**
     * Finds the biggest number from an array of numbers
     * @param absoulteValue determines if ignoring the signs of the numbers
     * @param nums
     * @param <T>
     * @return Type T that is that smallest of the numbers
     */
    public static <T extends Number & Comparable<T>> T findSmallest( boolean absoulteValue,  T ...nums)
    {
        T smallest ;
        T current ;
        if( nums != null && nums.length > 0 )
        {
            if( absoulteValue)
            {
                if (nums[0] instanceof Double || nums[0] instanceof Float) {
                    smallest = (T) new Double(Math.abs(nums[0].doubleValue()));

                }

                else
                {
                    smallest = (T) new Integer(Math.abs(nums[0].intValue()));

                }

            }
            
            else
            {
                smallest = nums[0];
            }
        }
        else
        {
            smallest = (T) new Integer(0);
        }

        for( int i = 1 ; i < nums.length ; i++ )
        {
            if( absoulteValue)
            {
                if (nums[i] instanceof Double || nums[i] instanceof Float) {
                    current = (T) new Double(Math.abs(nums[i].doubleValue()));

                }

                else
                {
                    current = (T) new Integer(Math.abs(nums[i].intValue()));

                }

            }
            
            else
            {
                current = nums[i];
            }
            if( smallest.compareTo(current) < 0 )
            {
                smallest = nums[i];
            }
        }

        return smallest;
    }

    /**
     * Finds the biggest number from an array of numbers
     * @param nums
     * @param <T>
     * @return Type T that is that largest of the numbers
     */
    public static <T extends Number & Comparable<T>> T findBiggest( boolean absoulteValue,  T ...nums)
    {
        T largest ;
        T current ;
        if( nums != null && nums.length > 0 )
        {
            if( absoulteValue)
            {
                if (nums[0] instanceof Double || nums[0] instanceof Float) {
                    largest = (T) new Double(Math.abs(nums[0].doubleValue()));

                }

                else
                {
                    largest = (T) new Integer(Math.abs(nums[0].intValue()));

                }

            }

            else
            {
                largest = nums[0];
            }
        }
        else
        {
            largest = (T) new Integer(0);
        }

        for( int i = 1 ; i < nums.length ; i++ )
        {
            if( absoulteValue)
            {
                if (nums[i] instanceof Double || nums[i] instanceof Float)
                {
                    current = (T) new Double(Math.abs(nums[i].doubleValue()));

                }

                else
                {
                    current = (T) new Integer(Math.abs(nums[i].intValue()));

                }

            }

            else
            {
                current = nums[i];
            }
            if( largest.compareTo(current) < 0 )
            {
                largest = nums[i];
            }
        }

        return largest;
    }
}
