import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        try{
            FileWriter myWriter = new FileWriter("data.txt");
            DecimalFormat df2 = new DecimalFormat("#.##");

        for(int i =0; i< 5000; i++)
        {
            double time =0; //minutes

            //Stage 0 - Drzemka             ##################################################################
            //| Tak   | Nie   |
            //| 1/4   | 3/4   |
            // Czas: 5-10 minut

            boolean s0 = bernoulli(0.25);
            //System.out.println("Berno s0: " +s0);
            if (s0 == true)
            {
                //System.out.println("Drzemka");
                double tempTime = udist(5,10);
                time += tempTime;
            }
            //System.out.println("After s0: " +time);

            //Stage 1 - Cwiczenia        #################################################################
            //| Tak                         | Nie   |
            //| 3/7                         | 4/7   |
            //|W domu       |Na zewnatrz|
            //|20-30 minut  |30-60 minut|
            boolean s1 = bernoulli(0.43);
            //System.out.println("Berno s1: " +s1);
            if(s1 == true)
            {
                boolean s1e1 = bernoulli(0.5);
                if(s1e1 == true)
                {
                    //System.out.println("Cw w DOmu");
                    double tempTime = udist(20,30);
                    time += tempTime;
                }else
                {
                    //System.out.println("Cw na zewnatrz");
                    double tempTime = udist(58,60);
                    time += tempTime;
                }
            }
            //System.out.println("After s1: " +time);

            //Stage 2 - Sniadanie   ##################################################################
            //| Tak                             | Nie     |
            //| 14/15                           | 1/15    |
            //|Jajecznica | Płatki  |Tosty      |
            //|10-15      |5-10     |10-20      |
            time += breakfast(0.3, 0.4, 0.3);

            //Stage 3 - Toaleta     ##################################################################
            time += udist(8,12);

            //Stage 4 - Ubieranie sie   ##################################################################
            //|Weekend                      | Tydzien
            //|2/7                          | 5/7
            //|Uczelnia                     | 5-8
            //|3/5 - Tak        |2/5 - Nie
            //|5 -8             |1-2
            boolean s4 = bernoulli(0.57);
            if(s4 == true)
            {
                boolean s4e1 = bernoulli(0.6);
                if(s4e1 == true)
                {
                    time += udist(5,8);
                }else
                {
                    time += udist(1,2);
                }
            }else
            {
                time += udist(5,8);
            }

            time = time*100;
            time = Math.round(time);
            time = time /100;

            //System.out.println(time);
            //String timeS = String.valueOf(time);

            myWriter.write(time+"\n");

        }
            myWriter.close();
        }catch (IOException e)
        {

        }






    }









    public static double udist(double min, double max)
    {
        double time = randomizer()*(max - min) + min;
        return time;
    }

    public static boolean bernoulli( double success)
    {
        boolean bernoVal;
        if(randomizer() <= success)
        {
            return bernoVal = true;
        }else
        {
            return  bernoVal = false;
        }
    }

    public static double breakfast (double con1, double con2, double con3)
    {
        double randVal =randomizer();
        if(randVal <= con1)
        {
            //jajecznica
            return udist(10,15);
        }else if(randVal <= con2)
        {
            //płatki
            return udist(5,10);
        }else
        {
            //tosty
            return udist(10,20);
        }
    }


    public static double randomizer(){
        Random rand = new Random();
        double myRand = rand.nextDouble();
        //System.out.println(myRand);
        return myRand;
    }

}
