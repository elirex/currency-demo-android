import json

import sys
import sqlite3
from sqlite3 import Error


def create_connection(db_file):
    conn = None
    try:
        conn = sqlite3.connect(db_file)
    except Error as e:
        print(e)
    return conn


def create_table(conn, create_table_sql):
    try:
        c = conn.cursor()
        c.execute(create_table_sql)
    except Error as e:
        print(e)


def read_json(file):
    data = None
    with open(file) as f:
        data = json.load(f)
    return data

def insert_currency(conn, currency):

    sql = """ INSERT INTO currencies(currency_id, name) VALUES(?,?)  """
    cur = conn.cursor()
    cur.execute(sql, currency)
    conn.commit()
    return cur.lastrowid

if __name__ == '__main__':
    currency_json = read_json('currency.json')
    conn = create_connection('./app.db')

    sql_create_currencies_table = """ CREATE TABLE IF NOT EXISTS currencies (
                                        currency_id TEXT NOT NULL PRIMARY KEY,
                                        name TEXT NOT NULL 
                                  ); """

    create_table(conn, sql_create_currencies_table)

    for c in currency_json:
        currency = (c['id'], c['name'])
        row_id = insert_currency(conn, currency) 
        print(row_id)

    if (conn is not None):
        conn.close()
