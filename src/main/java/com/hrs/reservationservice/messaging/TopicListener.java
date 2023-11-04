package com.hrs.reservationservice.messaging;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.hrs.reservationservice.models.PaymentDto;
import com.hrs.reservationservice.services.ReservationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TopicListener {

	@Value("${consumer.config.payment.topic.name}")
	private String paymentTopicName;

	@Value("${consumer.config.refund.topic.name}")
	private String refundTopicName;

	@Autowired
	private ReservationService reservationService;

	@KafkaListener(id = "${consumer.config.payment.topic.name}", topics = "${consumer.config.payment.topic.name}", groupId = "${consumer.config.group-id}")
	public void consumePayment(ConsumerRecord<String, PaymentDto> payload) {
		log.info("Topic : {}", paymentTopicName);
		log.info("Key : {}", payload.key());
		log.info("Headers : {}", payload.headers());
		log.info("Partion : {}", payload.partition());
		log.info("Payment : {}", payload.value());

		PaymentDto paymentDto = payload.value();
		//reservationService.updatePaymentStatus();
	}

	@KafkaListener(id = "${consumer.config.refund.topic.name}", topics = "${consumer.config.refund.topic.name}", groupId = "${consumer.config.group-id}")
	public void consumeRefund(ConsumerRecord<String, PaymentDto> payload) {
		log.info("Topic : {}", refundTopicName);
		log.info("Key : {}", payload.key());
		log.info("Headers : {}", payload.headers());
		log.info("Partion : {}", payload.partition());
		log.info("Payment : {}", payload.value());

		PaymentDto paymentDto = payload.value();
		//reservationService.updatePaymentStatus();
	}

}