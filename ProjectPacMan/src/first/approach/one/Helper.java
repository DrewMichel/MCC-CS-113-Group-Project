package first.approach.one;

import java.io.*;
import java.util.*;

/**
 * Created by Julian on 5/10/2017.
 * Helper.java - Has helper methods for the program
 */
public class Helper {

    public static final String SOURCE_LOCATION = "ProjectPacMan\\src\\first\\approach\\one\\";

    /**
     * Reads vertex info input and writes the graph implementation to a file for ease
     * @param fileName the name of the output file
     */
    public static void readGraphVertices( String fileName )
    {
        PrintWriter writer;
        Scanner key;
        FileOutputStream outStream;
        FileInputStream inStream ;
        Scanner reader ;
        File file;
        boolean finished ;
        String input ;
        ArrayList<Integer> coordinates ;
        String fileOutput ;
        int x1 , y1 , x2, y2, count , index, coordinate;
        coordinates = new ArrayList<>();
        file = new File( fileName);
        key = new Scanner( System.in);
        fileOutput = "";
        count = 0;

        try
        {
            // Creates a new file if the file doesn't exist



            reader = new Scanner( new FileInputStream( file ));
            writer = new PrintWriter( new FileOutputStream( new File(SOURCE_LOCATION + "TestVertices.txt" ),
                    true));
            count = 0;
            String line;
            StringTokenizer tokenizer ;
            count = 0;
            System.out.println("Reading file...");
            writer.println();
            writer.println("Next Append:");

            while( reader.hasNextLine())
            {
                line = reader.nextLine();
                tokenizer = new StringTokenizer(line," 0123456789");
                String[] split = line.split(" ");


                if( tokenizer.countTokens() == 1)
                {
                    String output = "";
                    writer.println( String.format("Path path%d = createPath( new Vertex( %d, %d ), Color.WHITE );", count,
                    Integer.parseInt(split[0]), Integer.parseInt(split[1]) ) );

                    if( split[2].contains("l"))
                    {
                        writer.println("path" + count + ".createLeftWall();");
                    }
                    else if( split[2].contains("r"))
                    {
                        writer.println("path" + count + ".createRightWall();");
                    }
                    if( split[2].contains("b"))
                    {
                        writer.println("path" + count + ".createBottomWall();");
                    }
                    if( split[2].contains("t"))
                    {
                        writer.println("path" + count + ".createTopWall();");
                    }

                    writer.println("mazePaths.add( path" + count + " );" );
                    count++;

                }

                if( split.length == 4)
                {
                    writer.println( String.format("Path path%d = createPath( new Vertex( %d, %d ), new Vertex( %d , %d ), Color.WHITE );",
                            count, Integer.parseInt(split[0]), Integer.parseInt(split[1]),
                            Integer.parseInt(split[2]), Integer.parseInt(split[3] )) );
                    writer.println("mazePaths.add( path" + count + " );" );

                    count++;


                }
                //coordinates.addAll( readInt( reader.nextLine()));
            }

            System.out.println( "Before writing : Count = " + count );
            /*for( int i = 0 ; i < count / 4 ; i++)
            {
                index = 4 * i;
                x1 = coordinates.get( index);
                y1 = coordinates.get(index + 1 );
                x2 = coordinates.get(index + 2);
                y2 = coordinates.get(index + 3);

                System.out.println( "i : " + i);
                //writer.println(String.format(" createPath( new Vertex( %d, %d ) , new Vertex(%d, %d ) )" +
            }
*/
            writer.close();
            reader.close();
        }

        catch (FileNotFoundException e) {
            System.out.println( "ERROR : File \"" + fileName + "\" not found.");
            System.out.println( e.getMessage());
            e.printStackTrace();

        }

        catch (IOException g)
        {
            System.out.println( "ERROR : Unknown problem.");
            System.out.println( g.getMessage());
        }

        key.close();


    }

    /**
     * Reads vertex info input and writes the graph implementation to a file for ease
     * @param fileName the name of the output file
     */
    public static void writeGraphVertices( String fileName )
    {
        PrintWriter writer;
        Scanner key;
        FileOutputStream outStream;
        FileInputStream inStream ;
        Scanner reader ;
        File file;
        boolean finished ;
        String input ;
        ArrayList<Integer> coordinates ;
        String fileOutput ;
        int x1 , y1 , x2, y2, count , index;

        coordinates = new ArrayList<>();
        file = new File( fileName);
        key = new Scanner( System.in);
        fileOutput = "";
        count = 0;
        try
        {
            // Creates a new file if the file doesn't exist
            if( !file.exists() )
            {
                file.createNewFile();
            }


            writer = new PrintWriter( new FileOutputStream( file , true));

            do
            {
                coordinates.addAll(
                        readInt( "Enter the edge coordinates with spaces in between : ", 4 , key));

                finished = askIfDone("Are you done : ", key);
                System.out.println("\n");

                count++;

            }while( !finished);

            for( int i = 0 ; i < count ; i++)
            {
                index = 4 * i;
                x1 = coordinates.get( index);
                y1 = coordinates.get(index + 1 );
                x2 = coordinates.get(index + 2);
                y2 = coordinates.get(index + 3);

                System.out.println( "i : " + i);
                writer.append(String.format("addEdge( %d, %d , %d, %d );%n",
                        x1 , y1 , x2 , y2) );
            }

            writer.close();
        }

        catch (FileNotFoundException e) {
            System.out.println( "ERROR : File \"" + fileName + "\" not found.");
            System.out.println( e.getMessage());
            e.printStackTrace();

        }

        catch (IOException g)
        {
            System.out.println( "ERROR : Unknown problem.");
            System.out.println( g.getMessage());
        }

        key.close();


    }

    public static boolean askIfDone( String prompt , Scanner key)
    {
        String input ;
        String[] inputSplit;
        ArrayList<Integer> list = new ArrayList<>();
        boolean validInput ;
        boolean isDone ;
        int num;

        do
        {
            validInput = true;


            System.out.println( prompt);
            input = key.nextLine();

            isDone = input.equalsIgnoreCase("Yes") || input.equalsIgnoreCase("Y");



        }while( !validInput);

        return isDone;

    }

    public static ArrayList<Integer> readInt( String prompt , int amount , Scanner key)
    {
        String input ;
        String[] inputSplit;
        ArrayList<Integer> list = new ArrayList<>();
        boolean validInput ;
        int num;

        do
        {
            validInput = false;

            try
            {
                System.out.println( prompt);
                input = key.nextLine();

                inputSplit = input.split(" ");

                for ( String inputs : inputSplit)
                {
                    num = Integer.parseInt( inputs);
                    list.add(num);
                }

                validInput = list.size() == amount ;

            }

            catch( NumberFormatException e)
            {
                System.out.println("ERROR : Invalid number entered, only integers allowed");
                list.clear();

            }

        }while( !validInput);

        return list;

    }

    /**
     * Breaks apart a String into ints
     * @param fileLine
     * @return
     */
    public static ArrayList<Integer> readInt( String fileLine)
    {
        String input ;
        String[] inputSplit;
        ArrayList<Integer> list = new ArrayList<>();
        boolean validInput ;
        int num ;

        try
        {


            inputSplit = fileLine.split(" ");

            for ( String inputs : inputSplit)
            {
                num = Integer.parseInt( inputs);
                list.add(num);
            }


        }

        catch( NumberFormatException e)
        {
            System.out.println("ERROR : Invalid number entered, only integers allowed");
            list.clear();

        }

        return list;

    }

    /**
     * Negates a boolean array
     * @param array the array to negate
     * @return boolean[] that is the negation of the original array
     */
    public static boolean[] negateArray( boolean[] array)
    {
        boolean[] newArray = new boolean[array.length];


        if( array == null )
        {
            return null;
        }

        newArray = new boolean[array.length];

        for(int i = 0 ; i  < array.length ; i++)
        {
            newArray[i] = !array[i];
        }

        return newArray;
    }

    /**
     * Check if an object is in a array
     * @param object the target object
     * @param array the array being checked
     * @param <T> the type of the object and array
     * @return boolean true if the object is in the array, else returns false
     */
    public static <T extends Object> boolean arrayContains( T object , T[] array)
    {
        boolean containsObject = false;
        int count = 0;

        // Check for null
        if( array == null )
        {
            return false;
        }

        // Check the elements in the array for the target object
        while( !containsObject && count < array.length )
        {
            containsObject = object.equals(array[count]);
            count++;

        }

        return containsObject;
    }

    /**
     * Converts an array of any type to a ArrayList
     * @param array the array to convert to a List
     * @return ArrayList that holds all the values in the array
     */
    public static <T extends Object> ArrayList<T> arrayToArrayList(T[] array)
    {
        ArrayList<T> list = new ArrayList<>();

        for ( int i = 0 ; i  < array.length ; i++ )
        {
            list.add( array[i]);
        }

        return list;

    }

    /**
     * Checks if the Collection and the array share at least one element
     * @param collection the collection being checked
     * @param array the array being checked
     * @param <T> the type the array and collection holds
     * @return boolean true if they share at least one element, else returns false
     */
    public static <T extends Object> boolean arraySharesElement(Collection<T> collection , T[] array )
    {
        boolean sharesAElement = false;
        int count ;

        if ( collection == null || collection.isEmpty() || array == null)
        {
            return false;
        }
        count = 0;

        // Check if the collection contains the elements in the array
        while( !sharesAElement && count < array.length)
        {
            sharesAElement = collection.contains( array[count]);
            count++;
        }

        return sharesAElement;
    }

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
     * Wrapper method finds the smallest number from a list of numbers
     * @param nums
     * @param <T>
     * @return T the smallest number
     */
    public static < T extends Number & Comparable<T>> T findSmallest( T ...nums)
    {
        return findSmallest(false, nums);
    }

    /**
     * Finds the smallest number from an array of numbers
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
     * Wrapper method finds the biggest number from a list of numbers
     * @param nums
     * @param <T>
     * @return T the biggest number
     */
    public static <T extends Number & Comparable<T>> T findBiggest( T ...nums)
    {
        return findBiggest(false, nums);
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
