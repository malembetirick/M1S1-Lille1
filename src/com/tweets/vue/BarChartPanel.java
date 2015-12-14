/**
 * 
 */
package com.tweets.vue;


import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.AxisTextAlignment;
import com.googlecode.charts4j.BarChart;
import com.googlecode.charts4j.BarChartPlot;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.Fills;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.LinearGradientFill;
import com.googlecode.charts4j.Plots;
import com.tweets.controleur.algos.statistiques.Temps;
import com.tweets.controleur.principal.Controleur;

/**
 * @author malem
 *
 */
public class BarChartPanel extends JPanel{
	
	Temps op;
	private Controleur cont;
	
    public static void setUpBeforeClass() throws Exception { 
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.ALL); 
    } 
    
    
	public BarChartPanel(Temps op) {
		this.op = op;
        // EXAMPLE CODE START
        // Defining data plots.
        BarChartPlot team1 = Plots.newBarChartPlot(Data.newData(25, 43, 12, 30), Color.GREEN, "Dictionnaire de mots-cles");
        BarChartPlot team2 = Plots.newBarChartPlot(Data.newData(8, 35, 11, 5), Color.BLUEVIOLET, "KNN");
        BarChartPlot team3 = Plots.newBarChartPlot(Data.newData(10, 20, 30, 30), Color.RED, "Bayesien Frequence uni+bigramme");

        // Instantiating chart.
        BarChart chart = GCharts.newBarChart(team1, team2, team3);

        // Defining axis info and styles
        AxisStyle axisStyle = AxisStyle.newAxisStyle(Color.BLACK, 13, AxisTextAlignment.CENTER);
        AxisLabels score = AxisLabelsFactory.newAxisLabels("Score", 50.0);
        score.setAxisStyle(axisStyle);
        AxisLabels year = AxisLabelsFactory.newAxisLabels("Year", 50.0);
        year.setAxisStyle(axisStyle);

        // Adding axis info to chart.
        //chart.addXAxisLabels(AxisLabelsFactory.newNumericAxisLabels(op.Temps(), op.Temps()+1, op.Temps()+2,op.Temps()+3));
        chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels("2002", "2003", "2004", "2005")); 
        chart.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0, 100));
        chart.addYAxisLabels(score);
        chart.addXAxisLabels(year);

        chart.setSize(500, 300);
        chart.setBarWidth(100);
        chart.setSpaceWithinGroupsOfBars(20);
        chart.setDataStacked(true);
        chart.setTitle("Evaluation du meilleur algorithme", Color.BLACK, 16);
        chart.setGrid(100, 10, 3, 2);
        chart.setBackgroundFill(Fills.newSolidFill(Color.ALICEBLUE));
        LinearGradientFill fill = Fills.newLinearGradientFill(0, Color.LAVENDER, 100);
        fill.addColorAndOffset(Color.WHITE, 0);
        chart.setAreaFill(fill);
        String url = chart.toURLString();
        // EXAMPLE CODE END. Use this url string in your web or
        // Internet application.
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(url);
        
    }



}
