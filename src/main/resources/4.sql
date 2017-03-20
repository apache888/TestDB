ALTER TABLE projects ADD cost INT DEFAULT 10000;

UPDATE projects SET cost = 20000 WHERE name_project = 'Messenger';
UPDATE projects SET cost = 30000 WHERE name_project = 'Aggregator';
UPDATE projects SET cost = 50000 WHERE name_project = 'Restaurant';
UPDATE projects SET cost = 25000 WHERE name_project = 'Tip-Tour';
UPDATE projects SET cost = 20000 WHERE name_project = 'Online Shop';
UPDATE projects SET cost = 40000 WHERE name_project = 'Store System';
UPDATE projects SET cost = 25000 WHERE name_project = 'Tickets-online';