package com.example.squareortriangularnumbersapp;

public class Number {
    int number;
    private boolean isTriangular;
    private boolean isSquare;

    private void isTriangular(){
        int x = 1;
        int triangularNumber = 0;

        for(;triangularNumber < number; x++){ //we put triangularNumber < number cz when it is equal and loop works the first task
            // is to add x in that numbber.So if the number is added then our number is skipped
            //and isTriangular will be set to false.
            triangularNumber += x;
            if(this.number == triangularNumber){
                isTriangular = true;
                return;
            }
        }
    }

    private void isSquare(){
        if(this.number % (Math.sqrt(this.number)) == 0.0){ // it takes square root of number then checks for the remainder with the number itself. IF the remainder is zero then the number is square.
            isSquare = true;
            return;
        }
    }

    public String typeOfNumber(){
        isSquare = false; //to be safe we are resetting the values every time cz this method is being called multiple times.
        isTriangular = false;

        isSquare();
        isTriangular();

        if(isSquare == true && isTriangular == true){
            return String.format("The number "+this.number+" is both triangular and square number.");
        }else if(isTriangular == true){
            return String.format("The number "+this.number+" is a triangular number.");
        }else if(isSquare == true){
            return String.format("The number "+this.number+" is a square number.");
        }else{
            return String.format("The number "+this.number+" is a neither a square nor a triangular number.");
        }

    }
}
