package exercicios;

import model.Commodity6;
import model.CommodityModel;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class Exercicio6 {
    public static void main(String args[]) {
        Logger.getLogger("org").setLevel(Level.ERROR);
        SparkConf conf = new SparkConf().setAppName("exercicio6").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> rddTransaction = sc.textFile("in/transactions.csv");
        JavaRDD<String> rddSemCabecalho = rddTransaction.filter(x -> !x.startsWith("country_or_area") && !x.endsWith("category"));
        JavaPairRDD<Commodity6,Float> prdd= rddSemCabecalho.mapToPair(l->{
            String[] colunas = l.split(";");
            String nameComm = colunas[3];
            String unit = colunas[7];
            int ano = Integer.parseInt(colunas[1]);
            float price;
            if(colunas[5].isEmpty()){
                price = 0;
            }
            else {
                price = Float.parseFloat(colunas[5]);
            }
            return new Tuple2<>(new Commodity6(nameComm,unit,ano),new Float(price));
        });
        JavaPairRDD<Commodity6,Float> ocorrencias = prdd.reduceByKey((x, y) -> x+y);
        JavaPairRDD<Commodity6,Float> ocorrenciasOrdenado = ocorrencias.mapToPair(x->x.swap()).sortByKey(false).mapToPair(x->x.swap());
        ocorrenciasOrdenado.coalesce(1).saveAsTextFile("output/Exercicio6");
    }
}
