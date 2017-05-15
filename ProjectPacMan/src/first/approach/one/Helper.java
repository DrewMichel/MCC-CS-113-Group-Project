package first.approach.one;

/**
 * Created by Julian on 5/10/2017.
 * Helper.java - Has helper methods for the program
 */
public class Helper {


    /**
     * Casts a generic number to the appropiate type for use
     * @param number
     * @param <T>
     * @return
     */
    public static <T extends Number & Comparable<T> > T castGenericNumber( T number)
    {
        T castedNum ;

        if (number instanceof Double )
        {
            castedNum = (T) new Double(number.doubleValue());
        }

        else if ( number instanceof Float)
        {

            castedNum = (T) new Float(number.floatValue());


        }

        else if ( number instanceof Integer)
        {
            castedNum = (T) new Integer(number.intValue());

        }

        else if ( number instanceof Long)
        {
            castedNum = (T) new Long(number.longValue());

        }

        else
        {
            castedNum = (T) new Integer(0 );
        }

        return castedNum ;
    }
    /**
     * Casts a generic number to the appropiate type for use
     * @param number
     * @param <G>
     * @param <T>
     * @return
     */
    public static < H extends Number & Comparable ,G extends Number & Comparable<H>, T extends Number & Comparable<H> > T castGenericNumber( T type, G number)
    {
        T castedNum ;

        if (type instanceof Double )
        {
            castedNum = (T) new Double(number.doubleValue());
        }

        else if ( type instanceof Float)
        {

            castedNum = (T) new Float(number.floatValue());


        }

        else if ( type instanceof Integer)
        {
            castedNum = (T) new Integer(number.intValue());

        }

        else if ( type instanceof Long)
        {
            castedNum = (T) new Long(number.longValue());

        }

        else
        {
            castedNum = (T) new Integer(0 );
        }

        return castedNum ;
    }

    /**
     * Finds the biggest number from an array of numbers
     * @param absoulteValue determines if ignoring the signs of the numbers
     * @param nums
     * @param <T>
     * @return Type T that is that smallest of the numbers
     */
    public static < T extends Number & Comparable<T>> T findSmallest( boolean absoulteValue,  T ...nums)
    {
        T smallest ;
        T smallestCompare  = null;
        T current ;

        // If there are numbers
        if( nums != null && nums.length > 0 )
        {
            smallest = nums[0];

            // If ignoring the number's signs
            if( absoulteValue)
            {

                // Store the first comparasion value with the first number's positive value
                smallestCompare = castGenericNumber( nums[0] ,  (T) castGenericNumber(Math.abs( nums[0].doubleValue() ) ));

            }

            else
            {
                smallestCompare = smallest;
            }
            

        }
        else
        {
            smallest = (T) new Integer(0);
        }

        // Finds the smallest value
        for( int i = 1 ; i < nums.length ; i++ )
        {
            // If ignoring the sign of the numbers
            if( absoulteValue)
            {
                // Get the positive version of the current number
                current = castGenericNumber( nums[i] ,  (T) castGenericNumber(Math.abs( nums[i].doubleValue() ) ));


            }
            
            else
            {
                current = nums[i];
            }

            // If found a new smallest number
            if( smallestCompare.compareTo(current) < 0 )
            {
                smallest = nums[i];

                // If ignoring the sign of the number
                if( absoulteValue)
                {

                    smallestCompare = castGenericNumber( nums[i] ,
                            (T) castGenericNumber(Math.abs( nums[i].doubleValue() ) ));


                }

                else
                {
                    smallest = nums[i];
                }
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
        T largestCompare  = null;
        T current ;

        // If there are numbers
        if( nums != null && nums.length > 0 )
        {
            largest = nums[0];

            // If ignoring the number's signs
            if( absoulteValue)
            {

                // Store the first comparasion value with the first number's positive value
                largestCompare = castGenericNumber( nums[0] ,  (T) castGenericNumber(Math.abs( nums[0].doubleValue() ) ));

            }

            else
            {
                largestCompare = largest;
            }


        }
        else
        {
            largest = (T) new Integer(0);
        }

        // Finds the largest value
        for( int i = 1 ; i < nums.length ; i++ )
        {
            // If ignoring the sign of the numbers
            if( absoulteValue)
            {
                // Get the positive version of the current number
                current = castGenericNumber( nums[i] ,  (T) castGenericNumber(Math.abs( nums[i].doubleValue() ) ));


            }

            else
            {
                current = nums[i];
            }

            // If found a new largest number
            if( largestCompare.compareTo(current) < 0 )
            {
                largest = nums[i];

                // If ignoring the sign of the number
                if( absoulteValue)
                {

                    largestCompare = castGenericNumber( nums[i] ,
                            (T) castGenericNumber(Math.abs( nums[i].doubleValue() ) ));


                }

                else
                {
                    largest = nums[i];
                }
            }
        }

        return largest;
    }
}
