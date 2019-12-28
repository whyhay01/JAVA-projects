package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String name;
        int endCondition;
        int width, thickness, length, minimumDimension, load;
        double modulusOfElasticity, compressionParallelToGrain, slendernessRatio , effectiveLength;
        double k =0;
        double divisionFactor, checker, eulerStress,experiencedStress;
        Scanner input = new Scanner(System.in);
        System.out.println("Hello, \n Please input your name.");
        name = input.nextLine();
        System.out.println("Hi " + name +"," + "\n This program helps you design your column by comparing  Euler's" +
                " stress to the expected experienced stress i.e P/A");
        System.out.println("Now let's begin, choose an end condition \n If both ends are fixed, enter 1. \n If both ends are pinned, enter 2." +
                "\n If one end is pinned and other fixed, enter 3. \n If one end is" +
                " free and the other fixed, enter 4");
        endCondition = input.nextInt();
        System.out.println("Input the load on the column");
        load = input.nextInt();
        System.out.println("Now to the trail section. \n Input the width of the column:");
        width = input.nextInt();
        System.out.println("Input the thickness:");
        thickness = input.nextInt();
        System.out.println("Input the length of the column");
        length = input.nextInt();
        System.out.println("Input the modulus of elasticity of the column material");
        modulusOfElasticity = input.nextDouble();
        System.out.println("Input the compression parallel to grain  of the column material");
        compressionParallelToGrain = input.nextDouble();

        if (width>thickness){
            minimumDimension= thickness;
        }else {
            minimumDimension = width;
        }
        if (endCondition==1){
            k = 0.7;
        }else if (endCondition ==2){
            k= 1.0;
        }else if (endCondition==3){
            k=0.7;
        }else if (endCondition==4){
            k=2.0;
        }

        effectiveLength = k*length;

        //Evaluating the slenderness ratio
        slendernessRatio = effectiveLength/minimumDimension;
        divisionFactor = modulusOfElasticity/compressionParallelToGrain;

        checker = 0.671 * Math.sqrt(divisionFactor);
        experiencedStress =(double) load/(width*thickness);
        System.out.println("P/A value" + experiencedStress);

        if (slendernessRatio > 11 && slendernessRatio> checker){
            System.out.println("Your designed column is LONG");
            eulerStress = (0.3 * modulusOfElasticity) / ((effectiveLength / minimumDimension) *
                    (effectiveLength / minimumDimension));
            System.out.println("Euler,s Stress: " + eulerStress );
            if (experiencedStress < eulerStress){
                System.out.println("The design is safe.");
            }else {
                System.out.println("The design will fail.");
            }
        }else if (slendernessRatio <= 11  ){
            System.out.println( "The designed column is SHORT");
            eulerStress = compressionParallelToGrain;
            System.out.println("The Maximum bearable stress is:  " + eulerStress);
            if (experiencedStress < eulerStress){
                System.out.println("The design is safe.");
            }else {
                System.out.println("The design will fail.");
            }
        }

       // System.out.println("minimumDimension = " + minimumDimension + "  "+"k = " +k+ " effective length =" + effectiveLength
        //+ " slenderness Ratio = " + slendernessRatio);
    }
}