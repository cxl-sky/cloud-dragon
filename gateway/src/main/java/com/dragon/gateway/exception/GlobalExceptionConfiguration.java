//package com.dragon.gateway.exception;
//
//import com.alibaba.fastjson.JSONObject;
//import com.dragon.exception.SystemErrorType;
//import com.dragon.response.Result;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.reactivestreams.Publisher;
//import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
//import org.springframework.core.annotation.Order;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.core.io.buffer.DataBufferFactory;
//import org.springframework.core.io.buffer.DataBufferUtils;
//import org.springframework.core.io.buffer.DefaultDataBufferFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ResponseStatusException;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Map;
//
//
///**
// * @author chenxiaolong
// */
//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class GlobalExceptionConfiguration implements ErrorWebExceptionHandler {
//    private final ObjectMapper objectMapper;
//
//    @Override
//    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
//        ServerHttpResponse response = exchange.getResponse();
//
//        if (response.isCommitted()) {
//            return Mono.error(ex);
//        }
//
//        // header set_json响应
//        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
//        //是否响应状态异常
//        Map<String, Object> handleResult = new GateWayExceptionHandlerAdvice().handle(ex);
//        if (ex instanceof ResponseStatusException) {
//            response.setStatusCode((HttpStatus) handleResult.get("status"));
//        }
//
//
////                Mono.fromSupplier(() -> {
//            DataBufferFactory bufferFactory = response.bufferFactory();
////            try {
////                //返回json异常原因给前端
////                return bufferFactory.wrap(objectMapper.writeValueAsBytes(handleResult.get("result")));
////            } catch (JsonProcessingException e) {
////                log.warn("Error writing response", ex);
////                return bufferFactory.wrap(new byte[0]);
////            }
////        })
//        ServerHttpResponseDecorator serverHttpResponseDecorator = new ServerHttpResponseDecorator(response) {
//            @Override
//            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
//                if (body instanceof Flux) {
//                    Flux<? extends DataBuffer> flux = (Flux<? extends DataBuffer>) body;
//                    return super.writeWith(flux.buffer().map(dataBuffers -> {
//                        DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
//                        //dataBuffer合并成一个，解决获取结果不全问题
//                        DataBuffer join = dataBufferFactory.join(dataBuffers);
//                        byte[] content = new byte[join.readableByteCount()];
//                        join.read(content);
//                        //释放掉内存
//                        DataBufferUtils.release(join);
//                        //formatResponse对获取的结果进行修改，可根据自身业务需求做修改
//                        String response = JSONObject.toJSONString(Result.fail(SystemErrorType.ACCESS_DENIED));
//                        //修改响应结果长度
//                        byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);
//
//                        return bufferFactory.wrap(responseBytes);
//                    }));
//                }
//                return super.writeWith(body);
//            }
//        };
//        return serverHttpResponseDecorator.
//    }
//}
