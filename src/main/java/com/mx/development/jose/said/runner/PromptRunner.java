package com.mx.development.jose.said.runner;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PromptRunner {

    @Bean
    public CommandLineRunner mejorado(ChatClient.Builder builder) {
        return args -> {

            ChatClient chatClient = builder.build();

            String requerimiento = """
                La empresa necesita una aplicación web
                para registrar clientes, consultar pedidos
                y generar reportes mensuales.
                """;

            String response = chatClient.prompt()
                    .system("""
                        Eres un analista de sistemas especializado
                        en levantamiento y análisis de requerimientos.

                        Reglas de comportamiento:
                        - Responde siempre en español.
                        - Utiliza un lenguaje profesional.
                        - No inventes información.
                        - Identifica claramente los datos faltantes.
                        - Diferencia requerimientos funcionales
                          y no funcionales.
                        """)
                    .user("""
                        Analiza el siguiente requerimiento:

                        <requerimiento>
                        %s
                        </requerimiento>

                        Presenta la respuesta con esta estructura:

                        ## Objetivo del sistema

                        ## Actores identificados

                        ## Requerimientos funcionales

                        ## Requerimientos no funcionales

                        ## Reglas de negocio

                        ## Información faltante
                        """.formatted(requerimiento))
                    .call()
                    .content();

            System.out.println("\nRESPUESTA DEL MODELO\n");
            System.out.println(response);
        };
    }



    @Bean
    public CommandLineRunner dinamico2(ChatClient.Builder builder) {
        return args -> {
            System.out.println();
            ChatClient chatClient = builder.build();
            String rol= """
                    Eres un arquitecto de aplicaciones especializado
                    en levantamiento de requerimientos.
                    """;

            String requerimiento = """
            La empresa necesita un sistema de registro de incidencias de seguridad,
            SOC, iSOC, CSIRT y CERT.
            """;

            String response = chatClient.prompt()
                    .system("""
                    %s

                    Responde siempre en español.
                    No inventes información.
                    Identifica cualquier dato faltante.
                    """.formatted(rol))
                    .user("""
                    Analiza el siguiente requerimiento:

                    %s

                    Devuelve:
                    1. Objetivo
                    2. Actores
                    3. Requerimientos funcionales
                    4. Requerimientos no funcionales
                    5. Información faltante
                    """.formatted(requerimiento))
                    .call()
                    .content();

            System.out.println(response);
        };
    }


    //@Bean
    public CommandLineRunner dinamico(ChatClient.Builder builder) {
        return args -> {
            System.out.println();
            ChatClient chatClient = builder.build();
            /*
            String requerimiento = """
            La empresa necesita un sistema para registrar clientes,
            consultar sus pedidos y generar reportes mensuales.
            """;*/
            String requerimiento = """
            La empresa necesita un sistema de registro de incidencias de seguridad,
            SOC, iSOC, CSIRT y CERT.
            """;

            String response = chatClient.prompt()
                    .system("""
                    Eres un analista de sistemas especializado
                    en levantamiento de requerimientos.

                    Responde siempre en español.
                    No inventes información.
                    Identifica cualquier dato faltante.
                    """)
                    .user("""
                    Analiza el siguiente requerimiento:

                    %s

                    Devuelve:
                    1. Objetivo
                    2. Actores
                    3. Requerimientos funcionales
                    4. Requerimientos no funcionales
                    5. Información faltante
                    """.formatted(requerimiento))
                    .call()
                    .content();

            System.out.println(response);
        };
    }
    //@Bean
    public CommandLineRunner estructurada(ChatClient.Builder builder) {
        return args -> {
            System.out.println();
            ChatClient chatClient = builder.build();
            String response = chatClient.prompt()
                    .user("""
							Analiza el siguiente requerimiento:
						
							La empresa necesita un sistema para registrar clientes,
							consultar pedidos y generar reportes.
						
							Identifica:/**/
							- actores;
							- requerimientos funcionales;
							- requerimientos no funcionales;
							- reglas de negocio;
							- información faltante.
						
							No inventes información.
							Responde en Markdown.
							""")
                    .call()
                    .content();
            System.out.println(response);
        };
    }

    //@Bean
    public CommandLineRunner generica(ChatClient.Builder builder) {
        return args -> {
            ChatClient chatClient = builder.build();
            String response = chatClient.prompt()
                    .user("""
                        Analiza este requerimiento:
                        La empresa necesita un sistema para registrar clientes,
                        consultar pedidos y generar reportes.
                        """)
                    .call()
                    .content();
            System.out.println(response);
        };
    }
    //@Bean
    public CommandLineRunner basic(ChatClient.Builder builder) {
        return args -> {
            System.out.println();
            System.out.println("Provider - OpenAI");
            ChatClient chatClient = builder.build();
            String response = chatClient.prompt("¿Que es Spring AI?")
                    .call()
                    .content();
            System.out.println(response);
        };
    }

}
