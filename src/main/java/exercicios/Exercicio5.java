package exercicios;


import model.CommodityAvr5;
import model.Media;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Exercicio5 {
    public static void main(String args[]) {
        int POSICAO_PAIS = 0;
        int POSICAO_FLOW = 4;
        Logger.getLogger("org").setLevel(Level.ERROR);
        SparkConf conf = new SparkConf().setAppName("exercicio5").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> rddTransaction = sc.textFile("in/transactions.csv");
        JavaRDD<String> rddSemCabecalho = rddTransaction.filter(x -> !x.startsWith("country_or_area") && !x.endsWith("category"));
        JavaRDD<String> rddBrazilAvg = rddSemCabecalho.filter(x-> x.split(";")[POSICAO_PAIS].equals("Brazil"));
        JavaRDD<String> rddExport = rddBrazilAvg.filter(x-> x.split(";")[POSICAO_FLOW].equals("Export"));

        JavaPairRDD<CommodityAvr5, CommodityAvr5> prdd = rddExport.mapToPair(l->{
            String[] colunas = l.split(";");
            int ano = Integer.parseInt(colunas[1]);
            float price;
            long quantity;
            String unit = colunas[7];
            String category = colunas[9];
            if(colunas[5].isEmpty()){
                price = 0;
            }
            else {
                price = Float.parseFloat(colunas[5]);
            }
            if (colunas[8].isEmpty()){
                quantity = 0;
            }
            else {
                quantity = Long.parseLong(colunas[8]);
            }
            return new Tuple2<>(new CommodityAvr5(unit,ano,category), new CommodityAvr5(price,1));
        });
        JavaPairRDD<CommodityAvr5,CommodityAvr5> fi = prdd.reduceByKey((x,y)-> new CommodityAvr5(x.getPrice()+y.getPrice(),x.getQtd()));
        JavaPairRDD<CommodityAvr5, Object> qtds = fi.mapValues(x->x.getPrice()/x.getQtd());
        JavaPairRDD<CommodityAvr5, Object> ocorrencias = qtds.sortByKey(true);
        ocorrencias.coalesce(1).saveAsTextFile("output/exercicio5");
    }
}
