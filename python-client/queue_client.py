#!/usr/bin/python

import array
import logging
import sys

import hazelcast


def main(args):
    clientConfig = hazelcast.ClientConfig()

    clientConfig.network_config.addresses.append("localhost")

    logging.basicConfig()
    logging.getLogger().setLevel(logging.INFO)

    client = hazelcast.HazelcastClient(clientConfig)

    queue = client.get_queue("binary-queue")

    while (1):
        item = queue.take()
        bArr = item.result()

        aStr = array.array('B', bArr).tostring()

        print(aStr)


if __name__ == "__main__":
    main(sys.argv)
