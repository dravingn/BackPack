import java.util.ArrayList;
import java.util.Vector;

public class Individual {

    private double gene;
    private int score;
    private double goal; //the answer.

    //random individual
    public Individual(double[] elements, int maxWeight) {
        while(gene < maxWeight){
            gene += elements;
        }
        calculateScore();
    }
    public Individual(Individual orig){ //copy constructor
        gene = orig.gene;
        goal = orig.goal;
        calculateScore();
    }


    public void calculateScore(){
        int sum = 0;
        for (int i = 0; i < gene.length(); i++) {
            char a = gene.charAt(i);
            char b = goal.charAt(i);
            int diff = Math.abs((int)a - (int)b);
            sum += diff;
        }
        score = sum;
    }
    public void mutate(double rateOfMutation){
        //should we have 100% chance of 1 mutation?!

        while(Math.random() < rateOfMutation){
            int randIndex = (int)(Math.random()*gene.length());
            char car = gene.charAt(randIndex);
            int diff = (int)(Math.random()*5) + 1;  //1-5
            if(Math.random() < .5){ //add
                int newCode = (int)car + diff;
                newCode = newCode > (int)'z' ? (int)'z' : newCode; //ternary
                car = (char)newCode;
            }else{ //subtract
                int newCode = (int)car - diff;
                newCode = newCode < (int)'a' ? (int)'a' : newCode; //ternary
                car = (char)newCode;
            }
            gene = gene.substring(0, randIndex) + car + gene.substring(randIndex + 1);
        }
        calculateScore();
    }

    public int getScore() {
        return score;
    }

    public String getGene() {
        return gene;
    }
}