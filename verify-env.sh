#!/bin/bash

echo "Verificando variáveis de ambiente..."

# Lista variáveis essenciais para o funcionamento
VARS=("PGHOST" "PGPORT" "PGDATABASE" "PGUSER" "PGPASSWORD")

# Verifica se cada variável está definida
missing=0
for var in "${VARS[@]}"; do
  if [ -z "${!var}" ]; then
    echo "⚠️ Variável $var não está definida!"
    missing=$((missing+1))
  else
    echo "✅ $var está definida"
  fi
done

# Testa conexão com PostgreSQL
echo ""
echo "Testando conexão com PostgreSQL..."
if command -v pg_isready > /dev/null; then
  pg_isready -h $PGHOST -p $PGPORT -U $PGUSER
  if [ $? -eq 0 ]; then
    echo "✅ Conexão com PostgreSQL bem-sucedida!"
  else
    echo "⚠️ Falha na conexão com PostgreSQL"
  fi
else
  echo "⚠️ Ferramenta pg_isready não disponível"
fi

if [ $missing -gt 0 ]; then
  echo ""
  echo "⚠️ $missing variáveis essenciais não estão definidas!"
  exit 1
else
  echo ""
  echo "✅ Todas as variáveis essenciais estão definidas!"
  exit 0
fi 