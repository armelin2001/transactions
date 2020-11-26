package exercicios;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class Exercicio1 {
    public static void main(String args[]){
        Logger.getLogger("org").setLevel(Level.ERROR);
        SparkConf conf = new SparkConf().setAppName("exercicio1").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> rddBrazil = sc.textFile("in/transactions.csv");
        JavaRDD<String> rddBraziSemCabecalho = rddBrazil.filter(x->!x.startsWith("country_or_area")&&!x.endsWith("category"));
        int POSICAO_PAIS = 0;
        JavaRDD<String> rddTransactionBrazil = rddBraziSemCabecalho
                .filter(x-> x.split(";")[POSICAO_PAIS].equals("Brazil"));
        JavaRDD<String> rddAno = rddTransactionBrazil.flatMap(l-> Arrays.asList(l.split(";")[POSICAO_PAIS]).iterator());
        JavaPairRDD<String,Integer> anoUnidade = rddAno.mapToPair(a->new Tuple2<>(a,1));
        JavaPairRDD<String,Integer> ocorrencias = anoUnidade.reduceByKey((x,y)->x+y);
        ocorrencias.coalesce(1).saveAsTextFile("output/exercicio1");
    }
}
