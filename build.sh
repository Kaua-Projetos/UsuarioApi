#!/bin/bash

# Script para construir o projeto para deploy no Railway

echo "Iniciando build para deploy no Railway..."

# Garantir que o Maven Wrapper tenha permissão de execução
chmod +x mvnw

# Limpar e empacotar a aplicação, pulando testes
./mvnw clean package -DskipTests

# Verificar se o build foi bem-sucedido
if [ $? -eq 0 ]; then
    echo "✅ Build concluído com sucesso!"
    echo "O arquivo JAR foi criado em: target/Gerenciador-de-Tarefas-0.0.1-SNAPSHOT.jar"
    echo ""
    echo "Para testar localmente:"
    echo "java -jar -Dspring.profiles.active=prod target/Gerenciador-de-Tarefas-0.0.1-SNAPSHOT.jar"
    echo ""
    echo "Para fazer deploy no Railway, siga as instruções no arquivo RAILWAY_SUPABASE_DEPLOY.md"
else
    echo "❌ Erro no build. Verifique os logs acima."
fi 