package exercicios;

import model.Commodity6;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class Exercicio2 {
    public static void main(String args[]){
        Logger.getLogger("org").setLevel(Level.ERROR);
        SparkConf conf = new SparkConf().setAppName("exercicio2").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> rddTransaction = sc.textFile("in/transactions.csv");
        JavaRDD<String> rddSemCabecalho = rddTransaction.filter(x->!x.startsWith("country_or_area")&&!x.endsWith("category"));
        int POSICAO_ANO = 1;
        JavaRDD<String> rddAno = rddSemCabecalho.flatMap(l-> Arrays.asList(l.split(";")[POSICAO_ANO]).iterator());
        JavaPairRDD<String,Integer> anoUnidade = rddAno.mapToPair(a->new Tuple2<>(a,1));
        JavaPairRDD<String,Integer> ocorrencias = anoUnidade.reduceByKey((x,y)->x+y);
        JavaPairRDD<String,Integer> ocorrenciasOrdenado = ocorrencias.sortByKey(true);
        ocorrenciasOrdenado.coalesce(1).saveAsTextFile("output/exercicio2");
    }
}
