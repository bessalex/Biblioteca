#!/bin/bash

psql -h localhost -U postgres <<EOF
     \i /docker-entrypoint-initdb.d/create_tables.sql
     \i /docker-entrypoint-initdb.d/fill_tables.sql
EOF

