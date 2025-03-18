# Deploy da API no Railway

Este guia explica como fazer o deploy da aplicação no Railway.

## Passos para deploy

1. **Crie uma conta no Railway**
   - Acesse [Railway](https://railway.app/) e crie uma conta

2. **Crie um novo projeto**
   - Selecione "New Project" no dashboard
   - Escolha "Deploy from GitHub repo"
   - Conecte seu repositório GitHub

3. **Configure as variáveis de ambiente**
   - Na seção "Variables", adicione as seguintes variáveis:
     ```
     SPRING_DATASOURCE_URL=jdbc:postgresql://${RAILWAY_INTERNAL_HOST}:${RAILWAY_INTERNAL_PORT}/${RAILWAY_DATABASE_NAME}
     SPRING_DATASOURCE_USERNAME=${RAILWAY_DATABASE_USERNAME}
     SPRING_DATASOURCE_PASSWORD=${RAILWAY_DATABASE_PASSWORD}
     SPRING_JPA_HIBERNATE_DDL_AUTO=update
     ```

4. **Adicione um banco de dados PostgreSQL**
   - Vá para "New" e selecione "Database" -> "PostgreSQL"
   - O Railway irá provisionar automaticamente uma instância PostgreSQL
   - As variáveis de ambiente para conexão serão automaticamente configuradas

5. **Deploy**
   - O Railway detectará automaticamente o Dockerfile e fará o build e deploy
   - Você pode acompanhar o progresso na aba "Deployments"

6. **Acesse sua aplicação**
   - Após o deploy bem-sucedido, clique em "Generate Domain" para obter uma URL pública
   - Sua API estará disponível nessa URL

## Solução de problemas

Se você encontrar problemas no build:

1. Verifique os logs de build no Railway
2. Certifique-se de que o arquivo `Dockerfile` está correto
3. Verifique se as permissões do arquivo `mvnw` estão corretas (executável)
4. Confirme que todas as variáveis de ambiente necessárias estão configuradas

## Estrutura do Projeto

O projeto usa um Dockerfile multi-stage para:
1. Construir a aplicação com Maven
2. Criar uma imagem menor apenas com o JRE e o JAR compilado

Esta abordagem otimiza o tamanho da imagem final e melhora o tempo de deploy. 