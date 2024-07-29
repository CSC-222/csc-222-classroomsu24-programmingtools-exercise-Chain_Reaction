import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ChainReaction {

    private int guessCount;
    private int chainLength;
    private int currentIndex;
    String []gameWords;
    private boolean chainSet;

    ArrayList<ArrayList<String>> wordSets;
    ArrayList<String[]> chainWords = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    public void playGame(){
        int guesses = 0;

        getWords();
        if(chainSet){
            createChain();
            updateChain();
            revealChainWord(0);
            revealChainWord(chainLength - 1);
            showChain();

            while(guesses <= getGuessCount()){
                System.out.println("Guesses Remaining: " + (getGuessCount() - guesses));
                System.out.print("Enter a guess for word " + (getCurrentIndex() + 1) + " :");
                String guess = input.nextLine().toLowerCase();
                if(guess.equals(gameWords[getCurrentIndex()])){
                    System.out.println("\nCorrect!....The word was " + guess);
                    revealChainWord(getCurrentIndex());
                    setCurrentIndex(getCurrentIndex()+1);
                    updateChain();
                    showChain();
                    if(getCurrentIndex() == chainLength - 1){
                        System.out.println("\nCONGRATULATIONS!  YOU HAVE COMPLETED THE CHAIN!\n");
                        break;
                    }
                }
                else{
                    System.out.println("\nIncorrect....Try Again");
                    updateChain();
                    showChain();
                }
                guesses++;
                if(guesses == getGuessCount()){
                    System.out.println("Sorry.  You have run out of guesses :(\n");
                    System.out.println("\nGAME OVER! :(");
                    System.out.println("Here is the chain:\n");
                    showChainWords();
                    break;
                }

            }
        }
    }


    public ChainReaction(int guesses, int chainLength,ArrayList<ArrayList<String>> set){
        this.guessCount = guesses;
        this.chainLength = chainLength;
        gameWords = new String[chainLength];
        wordSets = set;
        chainSet = false;
        currentIndex = 1;
    }

    public void showChain(){
        for (int i = 0; i < chainWords.size(); i++) {
            for (int j = 0; j < chainWords.get(i).length; j++) {
                System.out.print(chainWords.get(i)[j] + " ");
            }
            System.out.println();
        }
    }

    public void updateChain(){
        for (int i = 0; i < chainWords.size(); i++) {
            boolean letterSet = false;
            for (int j = 0; j < chainWords.get(i).length; j++) {
                if(chainWords.get(i)[j].equals("_")){
                    chainWords.get(i)[j] = String.valueOf(gameWords[i].charAt(j));
                    letterSet = true;
                    break;
                }
            }
            if(letterSet){
                break;
            }
        }
    }

    public void revealChainWord(int index){
        chainWords.set(index,new String[]{gameWords[index]});
    }

    public boolean validateChain(String word, ArrayList<ArrayList<String>> wordSets){
        for(int i = 0; i < wordSets.size(); i++){
            for(int j = 0; j < wordSets.get(i).size(); j++){
                boolean wordFound = false;
                for(int k = 0; k < wordSets.size(); k++){
                    if(word.equals(wordSets.get(k).get(0))){
                        wordFound = true;
                        break;
                    }
                }
                if(!wordFound){
                    return false;
                }
            }
        }
        return true;
    }

    public int getGuessCount() {
        return guessCount;
    }

    public void setGuessCount(int guessCount) {
        this.guessCount = guessCount;
    }

    public int getChainLength() {
        return chainLength;
    }

    public void setChainLength(int chainLength) {
        this.chainLength = chainLength;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }
}
