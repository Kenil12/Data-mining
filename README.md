# Data-mining
 
There is file named retail.txt contain dataset of retail store's itmes sold with other items. 
Main focus was to find pairs of itmeset from large number of buckets using A-Priori and PCY algorithm.
 
There are several classes I created for developing entire project. I used JAVA to develop the code. Following are the classes and their description: 
	A_Priori: in this class I defined algorithm for A-Priori with all the phases of A-Priori to find the frequent pair.
	PCY: algorithm for PCY with all the phases including hash function to find frequent pairs in complete dataset.
	Bucket: used to hold the memory.
	SetFile: Generating hash function and implanting into dataset.
	SetFile2: To override hash function. (but never used)
	Begin: Used to generate value from dataset.
	RunThis: This is the main function of entire project. It calculates the time for both algorithm for different percentage of support threshold for given dataset and return frequent pair according to percentage of data set given to function. (Here, I choose 1%, 5% and 10 % of support threshold given data set of retail store.) 
