package problems;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class QueryFormation {

    public static void generate(String [] args) {
        FileWriter fileWriter = null;
        double url = 0.1;
        double keywords = 0.1;
        double title =0.1;
        double body = 0.6;
        double inlink = 0.1;
        
        double and = 0.7;
        double near = 0.2;
        double window = 0.1;
        
        double w1 = 0;
        double w2 = 1;
        try {
            fileWriter = new FileWriter(new File("/Users/Caesar/Documents/query.txt"));

            fileWriter.append("10:#WAND ( " + w1 + " #AND( #WSUM(" + url + " cheap.url " + keywords + " cheap.keywords " + title + " cheap.title " + body + " cheap.body  " + inlink + " cheap.inlink)" +
                    " #WSUM(" + url + " internet.url " + keywords + " internet.keywords " + title + " internet.title " + body + " internet.body  " + inlink + " internet.inlink))" + w2 +
                    " #WAND(" + and + " #AND( cheap internet ) " + near + " #AND( #NEAR/1( cheap internet ) ) " + window + " #AND( #WINDOW/8( cheap internet ) ) )" + ")\n");
            
            //fileWriter.append("12:#WAND (" + w1 + " #AND( #WSUM(" + url + " djs.url " + keywords + " djs.keywords " + title + " djs.title " + body + " djs.body  " + inlink + " djs.inlink))" +
            //        w2 + " #WAND(" + and + " #AND( djs ) " + near + " #AND( #NEAR/1( djs ) ) " + window + " #AND( #WINDOW/4( djs) ) )" + ")\n");
            fileWriter.append("12:#AND(djs)\n");

            //lower heart rate
            fileWriter.append("26:#WAND ( " + w1 + " #AND( #WSUM(" + url + " lower.url " + keywords + " lower.keywords " + title + " lower.title " + body + " lower.body  " + inlink + " lower.inlink)" +
                    " #WSUM(" + url + " heart.url " + keywords + " heart.keywords " + title + " heart.title " + body + " heart.body  " + inlink + " heart.inlink)" + 
                    " #WSUM(" + url + " rate.url " + keywords + " rate.keywords " + title + " rate.title " + body + " rate.body  " + inlink + " rate.inlink))" + w2 +
                    " #WAND(" + and + " #AND( lower heart rate ) " + near + " #AND( #NEAR/1( lower heart rate ) ) " + window + " #AND( #WINDOW/12( lower heart rate ) ) )" + ")\n");
            //ps 2 games
            fileWriter.append(
            		"29:#WAND ( " + 
            		w1 + " #AND( " + 
            		" #WSUM(" + url + " ps.url " + keywords + " ps.keywords " + title + " ps.title " + body + " ps.body  " + inlink + " ps.inlink)" +
                    " #WSUM(" + url + " 2.url " + keywords + " 2.keywords " + title + " 2.title " + body + " 2.body  " + inlink + " 2.inlink)" +
                    " #WSUM(" + url + " game.url " + keywords + " game.keywords " + title + " game.title " + body + " game.body  " + inlink + " game.inlink)" +
                    ")" +
                    w2 + " #WAND(" + 
                    and + " #AND( ps 2 game ) " + 
                    near + " #AND( #NEAR/1( ps 2 game ) ) " + 
                    window + " #AND( #WINDOW/12( ps 2 game ) ) " + 
                    ")" + 
                    ")\n");
            //33:
            
            fileWriter.append(
            		"33:#WAND ( " + 
            		w1 + " #AND( " + 
                    " #WSUM(" + url + " elliptical.url " + keywords + " elliptical.keywords " + title + " elliptical.title " + body + " elliptical.body  " + inlink + " elliptical.inlink)" +
                    " #WSUM(" + url + " trainer.url " + keywords + " trainer.keywords " + title + " trainer.title " + body + " trainer.body  " + inlink + " trainer.inlink)" +
                    ")" + 
                    w2 + " #WAND(" + 
                    and + " #AND( elliptical trainer ) " + 
                    near + " #AND( #NEAR/1( elliptical trainer ) ) " + 
                    window + " #AND( #WINDOW/8( elliptical trainer ) ) " + 
                    ")" + 
                    ")\n");
            
            //52:avp
            fileWriter.append("52:#AND(avp)\n");
            /*fileWriter.append(
            		"52:#WAND ( " + 
            		w1 + " #AND( " + 
                    " #WSUM(" + url + " avp.url " + keywords + " avp.keywords " + title + " avp.title " + body + " avp.body  " + inlink + " avp.inlink)" +
                    ")" + 
                    w2 + " #WAND(" + 
                    and + " #AND( avp ) " + 
                    near + " #AND( #NEAR/1( avp ) ) " + 
                    window + " #AND( #WINDOW/4( avp ) ) " + 
                    ")" + 
                    ")\n");*/
            
            //71:living in india
            
            fileWriter.append(
            		"71:#WAND ( " + 
            		w1 + " #AND( " + 
            		" #WSUM(" + url + " living.url " + keywords + " living.keywords " + title + " living.title " + body + " living.body  " + inlink + " living.inlink)" +
                    //" #WSUM(" + url + " in.url " + keywords + " in.keywords " + title + " in.title " + body + " in.body  " + inlink + " in.inlink)" +
                    " #WSUM(" + url + " india.url " + keywords + " india.keywords " + title + " india.title " + body + " india.body  " + inlink + " india.inlink)" +
                    ")" +
                    w2 + " #WAND(" + 
                    and + " #AND( living in india ) " + 
                    near + " #AND( #NEAR/1( living in india ) ) " + 
                    window + " #AND( #WINDOW/12( living in india ) ) " + 
                    ")" + 
                    ")\n");
            
            //102:fickle creek farm
            
            fileWriter.append(
            		"102:#WAND ( " + 
            		w1 + " #AND( " + 
            		" #WSUM(" + url + " fickle.url " + keywords + " fickle.keywords " + title + " fickle.title " + body + " fickle.body  " + inlink + " fickle.inlink)" +
                    " #WSUM(" + url + " creek.url " + keywords + " creek.keywords " + title + " creek.title " + body + " creek.body  " + inlink + " creek.inlink)" +
                    " #WSUM(" + url + " farm.url " + keywords + " farm.keywords " + title + " farm.title " + body + " farm.body  " + inlink + " farm.inlink)" +
                    ")" +
                    w2 + " #WAND(" + 
                    and + " #AND( fickle creek farm ) " + 
                    near + " #AND( #NEAR/1( fickle creek farm ) ) " + 
                    window + " #AND( #WINDOW/12( fickle creek farm ) ) " + 
                    ")" + 
                    ")\n");
            
            //149:uplift at yellowstone national park
            
            fileWriter.append(
            		"149:#WAND ( " + 
            		w1 + " #AND( " + 
            		" #WSUM(" + url + " uplift.url " + keywords + " uplift.keywords " + title + " uplift.title " + body + " uplift.body  " + inlink + " uplift.inlink)" +
                    " #WSUM(" + url + " at.url " + keywords + " at.keywords " + title + " at.title " + body + " at.body  " + inlink + " at.inlink)" +
                    " #WSUM(" + url + " yellowstone.url " + keywords + " yellowstone.keywords " + title + " yellowstone.title " + body + " yellowstone.body  " + inlink + " yellowstone.inlink)" +
                    " #WSUM(" + url + " national.url " + keywords + " national.keywords " + title + " national.title " + body + " national.body  " + inlink + " national.inlink)" +
                    " #WSUM(" + url + " park.url " + keywords + " park.keywords " + title + " park.title " + body + " park.body  " + inlink + " park.inlink)" +
                    ")" +
                    w2 + " #WAND(" + 
                    and + " #AND( uplift at yellowstone national park ) " + 
                    near + " #AND( #NEAR/1( uplift at yellowstone national park ) ) " + 
                    window + " #AND( #WINDOW/20( uplift at yellowstone national park ) ) " + 
                    ")" + 
                    ")\n");
            
            //190:brooks brothers clearance
            fileWriter.append(
            		"190:#WAND ( " + 
            		w1 + " #AND( " + 
            		" #WSUM(" + url + " brooks.url " + keywords + " brooks.keywords " + title + " brooks.title " + body + " brooks.body  " + inlink + " brooks.inlink)" +
                    " #WSUM(" + url + " brothers.url " + keywords + " brothers.keywords " + title + " brothers.title " + body + " brothers.body  " + inlink + " brothers.inlink)" +
                    " #WSUM(" + url + " clearance.url " + keywords + " clearance.keywords " + title + " clearance.title " + body + " clearance.body  " + inlink + " clearance.inlink)" +
                    ")" +
                    w2 + " #WAND(" + 
                    and + " #AND(brooks brothers clearance ) " + 
                    near + " #AND( #NEAR/1( brooks brothers clearance ) ) " + 
                    window + " #AND( #WINDOW/12( brooks brothers clearance ) ) " + 
                    ")" + 
                    ")\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
