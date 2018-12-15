
### Notes

* `Pool` uses a `Hashtable` and will leak connections unless there is a synchronized block in `getConnection`.
* `Pool2` uses a `ConcurrentHashMap` and will also leak connections unless there is a synchronized block in `getConnection`.
