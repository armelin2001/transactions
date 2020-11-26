package exercicios;

import model.CommodityModel;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaPairRDD$;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exercicio3 {
    public static void main(String args[]) {
        int POSICAO_ANO = 1;
        int POSICAO_FLOW = 4;
        Logger.getLogger("org").setLevel(Level.ERROR);
        SparkConf conf = new SparkConf().setAppName("exercicio3").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> rddTransaction = sc.textFile("in/transactions.csv");
        JavaRDD<String> rddSemCabecalho = rddTransaction.filter(x -> !x.startsWith("country_or_area") && !x.endsWith("category"));

        JavaRDD<String> rddTransactionYear = rddSemCabecalho
                .filter(x -> x.split(";")[POSICAO_ANO].equals("2016"));


        JavaPairRDD<CommodityModel,Long>prdd= rddTransactionYear.mapToPair(l-> {
           String[] colunas = l.split(";");
           String flow = colunas[4];
           String nameComm = colunas[3];
           long quantity;
           if (colunas[8].isEmpty()){
               quantity = 0;
           }
           else {
               quantity = Long.parseLong(colunas[8]);
           }
           return new Tuple2<>(new CommodityModel(nameComm,flow), new Long(quantity));
        });
        JavaPairRDD<CommodityModel, Long> ocorrencias = prdd.reduceByKey((x, y) -> x+y);
        JavaPairRDD<CommodityModel,Long> ocorrenciasOrdenado = ocorrencias.mapToPair(x->x.swap()).sortByKey(false).mapToPair(x->x.swap());
        ocorrenciasOrdenado.coalesce(1).saveAsTextFile("output/Exercicio3");
    }
}

