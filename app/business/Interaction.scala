package business

trait Interaction[I, O] {
  def handle(request : I) : O
}