"local value = redis.call('GET', KEYS[1]) if value then redis.call('INCR', '#stats:hits') else redis.call('INCR', '#stats:miss') end return value" 1 #stats:miss
"-1"
