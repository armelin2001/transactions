package exercicios;

import model.Commodity7;
import model.CommodityAvr5;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class Exercicio7 {
    public static void main(String args[]) {
        Logger.getLogger("org").setLevel(Level.ERROR);
        SparkConf conf = new SparkConf().setAppName("exercicio7").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> rddTransaction = sc.textFile("in/transactions.csv");
        JavaPairRDD<Commodity7, Long> prdd = rddTransaction.mapToPair(l->{
            String[] colunas = l.split(";");
            String flow = colunas[4];
            String ano = colunas[1];
            return new Tuple2<>(new Commodity7(flow,ano),new Long(1));
        });
        JavaPairRDD<Commodity7,Long> ocorrencias = prdd.reduceByKey((x,y)->x+y);
        JavaPairRDD<Commodity7,Long> ocorrenciasOrdenado = ocorrencias.sortByKey(true);
        ocorrenciasOrdenado.coalesce(1).saveAsTextFile("output/exercicio7");
    }
    //country_or_area0 ;year1 ;comm_code2;commodity3;flow4;trade_usd5;weight_kg6;quantity_name7;quantity8;category

}
