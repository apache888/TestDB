ALTER TABLE projects ADD cost INT DEFAULT 10000;

UPDATE projects SET cost = 20000 WHERE name = 'Messenger';
UPDATE projects SET cost = 30000 WHERE name = 'Aggregator';
UPDATE projects SET cost = 50000 WHERE name = 'Restaurant';
UPDATE projects SET cost = 25000 WHERE name = 'Tip-Tour';
UPDATE projects SET cost = 20000 WHERE name = 'Online Shop';
UPDATE projects SET cost = 40000 WHERE name = 'Store System';
UPDATE projects SET cost = 25000 WHERE name = 'Tickets-online';