package ru.diasoft.micro.gcs;

import gcs.inter.gate.ibank.ws.types.WsCardBalDto;
import gcs.inter.gate.ibank.ws.types.WsCardBalResponseDto;
import gcs.inter.gate.ibank.ws.wsdl.CardBal;
import gcs.inter.gate.ibank.ws.wsdl.CardBalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigInteger;

@Endpoint
public class GcsEndpoint {
	private static final String NAMESPACE_URI = "http://ws.ibank.gate.inter.gcs/wsdl";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "cardBal")
	@ResponsePayload
	public CardBalResponse cardBal(@RequestPayload CardBal cardBal) {
		CardBalResponse response = new CardBalResponse();
		WsCardBalResponseDto wsCardBalResponseDto = new WsCardBalResponseDto();
		wsCardBalResponseDto.setMsgType(cardBal.getMsgType());
		wsCardBalResponseDto.setMsgData(cardBal.getMsgData());
		wsCardBalResponseDto.setSign(cardBal.getSign());
		wsCardBalResponseDto.setExtCode(cardBal.getExtCode());
		wsCardBalResponseDto.setExtId(cardBal.getExtId());

		WsCardBalDto data = new WsCardBalDto();
		data.setActBal(BigInteger.valueOf(10000));
		data.setExceedLmt(BigInteger.valueOf(10000));
		data.setCurrency("810");
		wsCardBalResponseDto.setData(data);

		response.setCardBalResult(wsCardBalResponseDto);
		return response;
	}
}