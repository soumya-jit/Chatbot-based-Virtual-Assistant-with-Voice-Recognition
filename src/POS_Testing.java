/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileInputStream; 
import java.io.InputStream;  

import opennlp.tools.postag.POSModel; 
import opennlp.tools.postag.POSSample; 
import opennlp.tools.postag.POSTaggerME; 
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.WhitespaceTokenizer;
/**
 *
 * @author soumajit
 */
public class POS_Testing
{
    public static void main(String[] args) throws Exception
    {
        //Loading Parts of speech-maxent model       
      InputStream inputStream = new FileInputStream("C:\\openNLP_models\\en-pos-maxent.bin"); 
      POSModel model = new POSModel(inputStream); 
       
      //Instantiating POSTaggerME class 
      POSTaggerME tagger = new POSTaggerME(model); 
       
      String sentence = "what is your favourite food john?"; 
       
      //Tokenizing the sentence using WhitespaceTokenizer class  
      SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;
      String[] tokens = simpleTokenizer.tokenize(sentence);
      //String[] db_tokens_tags = tagger.tag(db_qus_words);
      
      //Generating tags 
      String[] tags = tagger.tag(tokens);
      
      //Instantiating the POSSample class 
      POSSample sample = new POSSample(tokens, tags); 
      System.out.println(sample.toString());
    }
}
/*WP: Wh­pronoun
name: Noun, singular or mass
PRP$: Possessive pronoun
NN: Noun, singular or mass
NNP: Proper noun, singular*/

