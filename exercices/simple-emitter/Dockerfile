FROM python:3
MAINTAINER Jonathan Raffre <nk@nyuu.eu>

RUN mkdir /opt/sender && cp app/*.py /opt/sender
RUN cp app/requirements.txt /tmp/requirements.txt && pip install -r /tmp/requirements.txt && rm /tmp/requirements.txt

WORKDIR /opt/sender
CMD python .

EXPOSE 5000
