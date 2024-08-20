#!/bin/bash

# Wait for the vault_token.env file to be present
while [ ! -f /vault/file/vault_token.env ]; do
    echo "Waiting for vault_token.env file to be created..."
    sleep 1
done

# Source the vault_token.env file to export the VAULT_TOKEN environment variable
source /vault/file/vault_token.env

# Export the VAULT_TOKEN environment variable for the application
export VAULT_TOKEN

# Execute the original CMD ["./mvnw", "spring-boot:run"] with the environment variable set
exec "$@"