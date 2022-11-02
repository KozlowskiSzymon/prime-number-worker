create table if not exists t_primes (
    id bigserial primary key,
    value bigint,
    latest boolean
)