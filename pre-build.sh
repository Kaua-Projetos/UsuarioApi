#!/bin/bash

# Script de pré-build para garantir permissões corretas
echo "Configurando permissões para o build..."
chmod +x mvnw

# Verificar versão do Java
echo "Versão do Java:"
java -version

# Verificar versão do Maven
echo "Versão do Maven:"
./mvnw -v

echo "Pré-build concluído com sucesso!" 