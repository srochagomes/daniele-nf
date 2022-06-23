package com.daniele.nfe.routers;


import com.daniele.nfe.processors.NFeFailProcessor;
import com.daniele.nfe.processors.NFeSuccessProcessor;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class StartProcess extends RouteBuilder {
    @Override
    public void configure() throws Exception {


        from("{{route.from.input}}")
                .log("${body}")
                .doTry()
                    .unmarshal().jacksonXml()
                    .marshal().json(true)
                    .process(new NFeSuccessProcessor()).transform().body()
                    .to( "{{route.to.message.success}}")
                    .log("enviou ${body}")
                .doCatch(Throwable.class).onWhen(exceptionMessage().isNotNull())
                    .process(new NFeFailProcessor())
                    .log(exceptionMessage().toString())
                    .to("{{route.to.message.fail}}")
                    .throwException(new RuntimeException("Problema ao ler arquivo"))
                .end();
    }

}
