package quiz;
/**
 * The `QuestionsRepository` class manages a collection of quiz questions.
 * It provides access to individual questions by their index.
 */
public class QuestionsRepository {
	Question[] Questions = new Question[20]; // Array to store quiz questions
	
	/**
     * Constructs a new QuestionsRepository and initializes it with a set of predefined questions.
     */
	public QuestionsRepository() {
		Questions[0] = new Question("What is the largest country in the world by land area?", "Russia", "United States", "China", "Brazil");
		Questions[1] = new Question("Which river is the longest in the world?", "Amazon", "Nile", "Yangtze", "Mississippi");
		Questions[2] = new Question("In which continent is the Sahara Desert located?", "Africa", "Asia", "South America", "Australia");
		Questions[3] = new Question("What is the capital city of Japan?", "Tokyo", "Seoul", "Beijing", "Bangkok");
		Questions[4] = new Question("What is the currency of Australia?", "Australian Dollar", "Euro", "Yen", "Rupee");
		Questions[5] = new Question("Which ocean is the largest by surface area?", "Pacific Ocean", "Indian Ocean", "Atlantic Ocean", "Southern Ocean");
		Questions[6] = new Question("Which African country is known as the 'Rainbow Nation'?", "South Africa", "Kenya", "Nigeria", "Egypt");
		Questions[7] = new Question("What is the capital city of Canada?", "Ottawa", "Vancouver", "Toronto", "Montreal");
		Questions[8] = new Question("In which country would you find the ancient city of Petra?", "Jordan", "Egypt", "Greece", "Italy");
		Questions[9] = new Question("Which mountain range spans across the borders of France, Italy, Switzerland, and Austria?", "Alps", "Rocky Mountains", "Andes", "Himalayas");
		Questions[10] = new Question("Which country is known as the 'Land of the Rising Sun'?", "Japan", "China", "South Korea", "Vietnam");
		Questions[11] = new Question("What is the capital city of Brazil?", "Brasília", "Rio de Janeiro", "São Paulo", "Buenos Aires");
		Questions[12] = new Question("Which river flows through the Grand Canyon?", "Colorado River", "Missouri River", "Nile River", "Amazon River");
		Questions[13] = new Question("In which European city would you find the Eiffel Tower?", "Paris", "Rome", "Berlin", "Madrid");
		Questions[14] = new Question("What is the official language of Mexico?", "Spanish", "Portuguese", "French", "Italian");
		Questions[15] = new Question("Which desert is the largest hot desert in the world?", "Sahara Desert", "Arabian Desert", "Gobi Desert", "Karakum Desert");
		Questions[16] = new Question("What is the highest mountain in North America?", "Denali", "Mount McKinley", "Mount Logan", "Mount Elbert");
		Questions[17] = new Question("In which country would you find the ancient city of Machu Picchu?", "Peru", "Mexico", "Colombia", "Chile");
		Questions[18] = new Question("What is the capital city of South Korea?", "Seoul", "Tokyo", "Beijing", "Pyongyang");
		Questions[19] = new Question("What is the most amazing country in the world?", "Israel", "France", "England", "Belgium");

	}
	
    /**
     * Retrieves a specific quiz question based on its index.
     *
     * @param index The index of the desired question.
     * @return The quiz question at the specified index.
     */
	
	public Question getQuestion(int index) { 
		return Questions[index];
		
	}
	
	
}
