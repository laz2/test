require 'eventmachine'

class EchoServer < EventMachine::Connection
  def post_init
    puts "Connected"
  end

  def receive_data data
    send_data ">>> #{data}"
    close_connection if data =~ /quit/i
  end

  def unbind
    puts "Disconnected"
  end
end

EventMachine::run {
  EventMachine::start_server '', 8081, EchoServer
}
