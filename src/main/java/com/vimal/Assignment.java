package com.vimal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class Assignment {

	public Assignment() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		try
		{
			FileReader f1 = new FileReader(new File("C://Users//sandeep//Downloads//data.txt"));
			BufferedReader br = new BufferedReader(f1);
			String line = br.readLine();
			System.out.println(line);
			int Totalmin = Integer.parseInt((line.split(" "))[0]);
			int totalItems = Integer.parseInt((line.split(" "))[1]);
			int [] eatingTimes = new int[totalItems];
			int [] degreeOfSatisfactions = new int[totalItems];

			for(int i=0;i<totalItems;i++)
			{
				String itemline = br.readLine();
				eatingTimes[i] = Integer.parseInt((itemline.split(" "))[1]);
				degreeOfSatisfactions[i] = Integer.parseInt((itemline.split(" "))[0]);
				
			}
			br.close();
			
			System.out.println("=================finally result is ---"+getMaxSatisfaction(Totalmin,eatingTimes,degreeOfSatisfactions,totalItems));

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


	}

	public static int getMaxSatisfaction(int timeLimit, int eatingTimes[], int degreeOfSatisfactions[],int menuItemCount) {
		if (menuItemCount != eatingTimes.length || menuItemCount != degreeOfSatisfactions.length) {

		}
		int i, w;
		int satisfactionMatrix[][] = new int[menuItemCount + 1][timeLimit + 1];
		for (i = 0; i <= menuItemCount; i++) {
			for (w = 0; w <= timeLimit; w++) {
				if (i == 0 || w == 0)
					satisfactionMatrix[i][w] = 0;
				else if (eatingTimes[i - 1] <= w)
					satisfactionMatrix[i][w] = Math.max(
							degreeOfSatisfactions[i - 1] + satisfactionMatrix[i - 1][w - eatingTimes[i - 1]],
							satisfactionMatrix[i - 1][w]);
				else
					satisfactionMatrix[i][w] = satisfactionMatrix[i - 1][w];
			}
		}

		return satisfactionMatrix[menuItemCount][timeLimit];
	}

}
