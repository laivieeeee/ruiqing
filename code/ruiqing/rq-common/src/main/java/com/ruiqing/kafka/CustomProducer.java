package com.ruiqing.kafka;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/11/11 15:33
 */

public class CustomProducer {
    private static String BOOTSTRAP_SERVERS = "192.168.194.102:9092";
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        producerCallBack();
    }

    public static void producelFutureGet() throws ExecutionException, InterruptedException {
        Properties props = new Properties();

        //kafka集群，broker-list
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);

        props.put("acks", "all");

        //重试次数
        props.put("retries", 1);

        //批次大小
        props.put("batch.size", 16384);

        //等待时间
        props.put("linger.ms", 1);

        //RecordAccumulator缓冲区大小
        props.put("buffer.memory", 33554432);

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<>("test", Integer.toString(i), Integer.toString(i))).get();
        }
        producer.close();
    }

    public static void producelNotCallBack() {
        Properties props = new Properties();

        //kafka集群，broker-list
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);

        props.put("acks", "all");

        //重试次数
        props.put("retries", 1);

        //批次大小
        props.put("batch.size", 16384);

        //等待时间
        props.put("linger.ms", 1);

        //RecordAccumulator缓冲区大小
        props.put("buffer.memory", 33554432);

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<>("test", i+"key", i+"value"));
        }

        producer.close();
    }

    // 回调
    public static void producerCallBack() throws ExecutionException, InterruptedException {

        Properties props = new Properties();

        //kafka集群，broker-list
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);

        props.put("acks", "all");

        //重试次数
        props.put("retries", 1);

        //批次大小
        props.put("batch.size", 16384);

        //等待时间
        props.put("linger.ms", 1);

        //RecordAccumulator缓冲区大小
        props.put("buffer.memory", 33554432);

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 100; i++) {
            //回调函数，该方法会在Producer收到ack时调用，为异步调用
            producer.send(new ProducerRecord<>("test", Integer.toString(i), Integer.toString(i)), (metadata, exception) -> {
                if (exception == null) {
                    System.out.println("success->" + metadata.offset());
                } else {
                    exception.printStackTrace();
                }
            });
        }
        producer.close();
    }
}
